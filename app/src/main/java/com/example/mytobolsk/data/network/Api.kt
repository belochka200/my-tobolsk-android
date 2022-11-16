package com.example.mytobolsk.data.network

import com.example.mytobolsk.data.models.Event

interface Api {
    suspend fun getAllEvent(): List<Event>
}

class ApiImpl : Api {
    override suspend fun getAllEvent(): List<Event> {
        return listOf(
            Event(1, "Мероприятие 1")
        )
    }
}