package com.example.mytobolsk.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: Int,
    val title: String,
    val describe: String,
    val date: String,
    val time: String,
    val place: String,
)
