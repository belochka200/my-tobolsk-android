package com.example.mytobolsk.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val id: Int,
    val title: String,
    val describe: String
)
