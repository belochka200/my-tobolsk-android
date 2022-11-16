package com.example.mytobolsk.ui.states

import com.example.mytobolsk.ui.models.Event

sealed class MainScreenUiState {
    object Loading : MainScreenUiState()
    object Error : MainScreenUiState()
    data class Content(
        val events: List<Event>
    ) : MainScreenUiState()
}