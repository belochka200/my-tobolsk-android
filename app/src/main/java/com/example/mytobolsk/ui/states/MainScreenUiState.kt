package com.example.mytobolsk.ui.states

import com.example.mytobolsk.ui.models.Event
import com.example.mytobolsk.ui.models.Route
import com.example.mytobolsk.ui.models.Story

sealed class MainScreenUiState {
    object Loading : MainScreenUiState()
    object Error : MainScreenUiState()
    data class Content(
        val events: List<Event>,
        val stories: List<Story>,
        val routes: List<Route>
    ) : MainScreenUiState()
}