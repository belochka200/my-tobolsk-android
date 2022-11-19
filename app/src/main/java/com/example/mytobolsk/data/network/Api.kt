package com.example.mytobolsk.data.network

import com.example.mytobolsk.data.models.Event
import com.example.mytobolsk.data.models.Story
import com.google.firebase.database.FirebaseDatabase

interface Api {
    suspend fun getAllEvent(): List<Event>
    suspend fun getAllStories(): List<Story>
}

class ApiImpl : Api {
    override suspend fun getAllEvent(): List<Event> {
//        val database = FirebaseDatabase.getInstance().reference
//        database.child("routes").get().addOnSuccessListener {}
        return listOf(
            Event(1, "Мероприятие 1")
        )
    }

    override suspend fun getAllStories(): List<Story> {
        return listOf(
            Story(1, "Story 1"),
            Story(2, "Story 2"),
            Story(3, "Story 3"),
            Story(4, "Story 4"),
            Story(5, "Story 5"),
        )
    }
}