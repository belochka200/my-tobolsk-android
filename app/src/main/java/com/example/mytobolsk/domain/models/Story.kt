package com.example.mytobolsk.domain.models

data class Story(
    val id: Int,
    val title: String,
    val image: String,
    val describe: String,
    val date: String,
    val time: String
)

fun List<Story>.asUiModel(): List<com.example.mytobolsk.ui.models.Story> {
    return map {
        com.example.mytobolsk.ui.models.Story(
            id = it.id,
            title = it.title,
            image = it.image,
            describe = it.describe,
            date = it.date,
            time = it.time
        )
    }
}