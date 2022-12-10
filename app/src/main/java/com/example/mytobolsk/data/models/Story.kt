package com.example.mytobolsk.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Story(
    val id: Int,
    val title: String,
    val describe: String
)
