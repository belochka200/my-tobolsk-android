package com.example.mytobolsk.ui.states

data class EventItemUiState(
    val title: String,
    val time: String,
    val place: String,
    val date: String,
    val bookmarked: Boolean = true,
//    val onBookmark: () -> Unit
)