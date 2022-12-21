package com.example.mytobolsk.ui.models

data class Event(
    val id: Int,
    val title: String,
    val image: String,
    val describe: String,
    val date: String,
    val time: String,
    val place: String,
//    val bookmarked: Boolean = false,
)
