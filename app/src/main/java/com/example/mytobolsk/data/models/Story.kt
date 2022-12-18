package com.example.mytobolsk.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Story(
    @PrimaryKey val id: Int,
    val title: String,
    val image: String,
    val describe: String,
    val date: String,
    val time: String
)

fun List<Story>.asDomainModel(): List<com.example.mytobolsk.domain.models.Story> {
    return map {
        com.example.mytobolsk.domain.models.Story(
            id = it.id,
            title = it.title,
            image = it.image,
            describe = it.describe,
            date = it.date,
            time = it.time
        )
    }
}