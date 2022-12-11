package com.example.mytobolsk.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytobolsk.domain.usecases.LoadMainScreenImpl
import com.example.mytobolsk.ui.models.Event
import com.example.mytobolsk.ui.models.Route
import com.example.mytobolsk.ui.models.Story
import com.example.mytobolsk.ui.states.MainScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {
    private val _uiState = MutableLiveData<MainScreenUiState>(MainScreenUiState.Loading)
    val uiState: LiveData<MainScreenUiState> = _uiState

    private var job: Job? = null

    init {
        fetchData()
    }

    fun fetchData() {
        job?.cancel()
        _uiState.value = MainScreenUiState.Loading
        job = viewModelScope.launch(Dispatchers.IO) {
            try {
                val events: List<Event> = LoadMainScreenImpl().getAllEvents().map {
                    Event(
                        id = it.id,
                        title = it.title,
                        describe = it.describe,
                        date = it.date,
                        time = it.time,
                        place = it.place
                    )
                }
                val stories: List<Story> = LoadMainScreenImpl().getAllStories().map {
                    Story(
                        id = it.id,
                        title = it.title,
                        describe = it.describe,
                        date = it.date,
                        time = it.time
                    )
                }
                val routes: List<Route> = LoadMainScreenImpl().getAllRoutes().map {
                    Route(
                        id = it.id,
                        title = it.title,
                        describe = it.describe
                    )
                }
                _uiState.postValue(
                    MainScreenUiState.Content(
                        events = events,
                        stories = stories,
                        routes = routes
                    )
                )
            } catch (e: Exception) {
                Log.e("Loading API Error", e.toString())
                _uiState.postValue(MainScreenUiState.Error)
            }
        }
    }
}