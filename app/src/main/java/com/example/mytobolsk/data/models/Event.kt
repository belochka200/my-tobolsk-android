package com.example.mytobolsk.data.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Event(
    val title: String? = null,
    val time: String? = null,
    val place: String? = null,
    val date: String? = null,
    val image: String? = null,
)
