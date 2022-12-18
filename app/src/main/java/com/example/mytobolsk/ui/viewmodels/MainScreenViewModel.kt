package com.example.mytobolsk.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mytobolsk.domain.models.asUiModel
import com.example.mytobolsk.domain.usecases.LoadMainScreenImpl
import com.example.mytobolsk.ui.models.Event
import com.example.mytobolsk.ui.models.Route
import com.example.mytobolsk.ui.models.Story
import com.example.mytobolsk.ui.states.MainScreenUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainScreenViewModel(application: Application) : AndroidViewModel(application) {
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
                val events: List<Event> = LoadMainScreenImpl().getAllEvents().asUiModel()
                val stories: List<Story> = LoadMainScreenImpl().getAllStories().asUiModel()
                val routes: List<Route> = LoadMainScreenImpl().getAllRoutes().asUiModel()
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

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainScreenViewModel::class.java)) {
                return MainScreenViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}