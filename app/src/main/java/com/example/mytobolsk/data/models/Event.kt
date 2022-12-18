package com.example.mytobolsk.data.models

data class Event(
    val id: Int,
    val title: String,
    val describe: String,
    val date: String,
    val time: String,
    val place: String,
)

fun List<Event>.asDomainModel(): List<com.example.mytobolsk.domain.models.Event> {
    return map {
        com.example.mytobolsk.domain.models.Event(
            id = it.id,
            title = it.title,
            describe = it.describe,
            date = it.date,
            time = it.time,
            place = it.place
        )
    }
}
