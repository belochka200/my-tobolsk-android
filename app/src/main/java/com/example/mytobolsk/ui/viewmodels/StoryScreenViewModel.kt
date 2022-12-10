package com.example.mytobolsk.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytobolsk.domain.usecases.LoadStoryByIdImpl
import com.example.mytobolsk.ui.states.StoryScreenUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class StoryScreenViewModel : ViewModel() {
    private val _uiState = MutableLiveData<StoryScreenUiState>(StoryScreenUiState.Loading)
    val uiState = _uiState
    private var job: Job? = null

    fun fetchStory(storyId: Int) {
        job?.cancel()
        job = viewModelScope.launch {
            _uiState.value = StoryScreenUiState.Loading
            try {
                val story = LoadStoryByIdImpl().getStory(storyId)
                _uiState.postValue(StoryScreenUiState.Content(
                    title = story.title,
                    describe = story.describe
                ))
            } catch (_: Exception) {
                _uiState.postValue(StoryScreenUiState.Error)
            }
        }
    }
}