package com.example.mytobolsk.data.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Story(
    val id: Int? = null,
    val title: String? = null
)
