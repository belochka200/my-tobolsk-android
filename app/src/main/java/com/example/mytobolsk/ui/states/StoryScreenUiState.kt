package com.example.mytobolsk.ui.states

sealed class StoryScreenUiState {
    object Loading : StoryScreenUiState()
    object Error : StoryScreenUiState()
    data class Content(
        val title: String,
        val describe: String
    ) : StoryScreenUiState()
}