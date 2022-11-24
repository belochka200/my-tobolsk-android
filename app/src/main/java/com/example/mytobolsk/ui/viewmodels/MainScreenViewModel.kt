package com.example.mytobolsk.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytobolsk.domain.usecases.LoadMainScreenImpl
import com.example.mytobolsk.ui.models.Event
import com.example.mytobolsk.ui.models.Route
import com.example.mytobolsk.ui.models.Story
import com.example.mytobolsk.ui.states.MainScreenUiState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {
    private val _uiState = MutableLiveData<MainScreenUiState>(MainScreenUiState.Loading)

    val uiState: LiveData<MainScreenUiState> = _uiState
    private var job: Job? = null

    init {
        fetchData()
    }

    private fun fetchData() {
        val databaseReferenceRoutes = FirebaseDatabase.getInstance().getReference("routes")
        job?.cancel()
        job = viewModelScope.launch {
            try {
                val stories: List<Story> = LoadMainScreenImpl().getAllStories().map {
                    Story(
                        title = it.title
                    )
                }
                val events: List<Event> = LoadMainScreenImpl().getAllEvents().map {
                    Event(
                        title = it.title
                    )
                }
//                val routes: List<Route> = LoadMainScreenImpl().getAllRoutes().map {
//                    Route(
//                        title = it.title
//                    )
//                }

                databaseReferenceRoutes.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val retrieveRoutesList: MutableList<com.example.mytobolsk.data.models.Route> =
                            mutableListOf()
                        (snapshot.children).forEach { route ->
                            retrieveRoutesList.add(route.getValue(com.example.mytobolsk.data.models.Route::class.java)!!)
                        }
                        val routes: List<Route> = retrieveRoutesList.map {
                            Route(
                                title = it.title!!
                            )
                        }
//                        _storiesListUpdated.value = routesList.map {
//                            Route(
//                                title = it.title!!
//                            )
//                        }
                        _uiState.postValue(
                            MainScreenUiState.Content(
                                events = events, stories = stories, routes = routes
                            )
                        )
                    }

                    override fun onCancelled(error: DatabaseError) {}
                })
            } catch (_: Exception) {
                _uiState.postValue(MainScreenUiState.Error)
            }
        }
    }
}