package com.example.mytobolsk.domain.models

data class Event(
    val id: Int,
    val title: String,
    val describe: String,
    val date: String,
    val time: String,
    val place: String,
)

fun List<Event>.asUiModel(): List<com.example.mytobolsk.ui.models.Event> {
    return map {
        com.example.mytobolsk.ui.models.Event(
            id = it.id,
            title = it.title,
            describe = it.describe,
            date = it.date,
            time = it.time,
            place = it.place
        )
    }
}