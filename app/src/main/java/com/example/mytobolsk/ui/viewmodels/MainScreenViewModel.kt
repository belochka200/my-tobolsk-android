package com.example.mytobolsk.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytobolsk.domain.usecases.LoadAllEventUseCaseImpl
import com.example.mytobolsk.domain.usecases.LoadStoriesUseCaseImpl
import com.example.mytobolsk.ui.models.Event
import com.example.mytobolsk.ui.models.Story
import com.example.mytobolsk.ui.states.MainScreenUiState
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
        job?.cancel()
        job = viewModelScope.launch {
            try {
                val stories: List<Story> = LoadStoriesUseCaseImpl().getAllStories().map {
                    Story(
                        title = it.title
                    )
                }
                val events: List<Event> = LoadAllEventUseCaseImpl().getAllEvent().map {
                    Event(
                        id = it.id,
                        title = it.title
                    )
                }
                _uiState.postValue(MainScreenUiState.Content(events, stories))
            } catch (_: Exception) {
                _uiState.postValue(MainScreenUiState.Error)
            }
        }
    }
}