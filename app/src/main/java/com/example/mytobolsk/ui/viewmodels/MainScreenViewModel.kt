package com.example.mytobolsk.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytobolsk.ui.models.Route
import com.example.mytobolsk.ui.models.Story
import com.example.mytobolsk.ui.states.EventItemUiState
import com.example.mytobolsk.ui.states.MainScreenUiState
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.example.mytobolsk.data.models.Event as EventAsData
import com.example.mytobolsk.data.models.Route as RouteAsData
import com.example.mytobolsk.data.models.Story as StoryAsData

class MainScreenViewModel : ViewModel() {
    private val _uiState = MutableLiveData<MainScreenUiState>(MainScreenUiState.Loading)
    val uiState: LiveData<MainScreenUiState> = _uiState

    private var job: Job? = null

    init {
        fetchData()
    }

    private fun fetchData() {
        val database = FirebaseDatabase.getInstance().reference
        job?.cancel()
        job = viewModelScope.launch {
            try {
                database.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val retrieveRoutesList: MutableList<RouteAsData> = mutableListOf()
                        val retrieveStoriesList: MutableList<StoryAsData> = mutableListOf()
                        val retrieveEventsList: MutableList<EventAsData> = mutableListOf()
                        (snapshot.children).forEach {
                            when (it.key) {
                                "routes" -> {
                                    it.children.forEach { route ->
                                        retrieveRoutesList.add(route.getValue(RouteAsData::class.java)!!)
                                    }
                                }
                                "stories" -> {
                                    it.children.forEach { story ->
                                        val storyToList = story.getValue(StoryAsData::class.java)!!
                                        retrieveStoriesList.add(storyToList)
                                    }
                                }
                                "events" -> {
                                    it.children.forEach { event ->
                                        retrieveEventsList.add(event.getValue(EventAsData::class.java)!!)
                                    }
                                }
                            }
                        }
                        val routes: List<Route> = retrieveRoutesList.map {
                            Route(
                                title = it.title!!
                            )
                        }
                        val stories: List<Story> = retrieveStoriesList.map {
                            Story(
                                id = it.id!!,
                                title = it.title!!
                            )
                        }
                        val events: List<EventItemUiState> = retrieveEventsList.map {
                            EventItemUiState(
                                title = it.title!!,
                                time = it.time!!,
                                place = it.place!!,
                                date = it.date!!,
                            )
                        }
                        _uiState.postValue(
                            MainScreenUiState.Content(
                                events = events, stories = stories, routes = routes
                            )
                        )
                    }

                    override fun onCancelled(error: DatabaseError) {
                        _uiState.postValue(MainScreenUiState.Error)
                    }
                })
            } catch (_: Exception) {
                _uiState.postValue(MainScreenUiState.Error)
            }
        }
    }
}