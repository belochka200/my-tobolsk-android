package com.example.mytobolsk.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytobolsk.ui.states.MainScreenUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {
    private val _uiState = MutableLiveData<MainScreenUiState>(MainScreenUiState.Loading)

    val uiState: LiveData<MainScreenUiState> = _uiState
    private var job: Job? = null

    // TODO() дописать функцию
    fun fetchEvents() {
        job?.cancel()
        job = viewModelScope.launch {
            try {

            } catch (_: Exception) {

            }
        }
    }
}