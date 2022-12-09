package com.example.mytobolsk.data.network

import com.example.mytobolsk.data.models.Event
import com.example.mytobolsk.data.models.Route
import com.example.mytobolsk.data.models.Story

interface Api {
    suspend fun getAllEvent(): List<Event>
    suspend fun getAllStories(): List<Story>
    suspend fun getAllRoutes(): List<Route>
}

class ApiImpl : Api {
    override suspend fun getAllEvent(): List<Event> {
        return listOf(
            Event("Мероприятие 1")
        )
    }

    override suspend fun getAllStories(): List<Story> {
        return listOf(
            Story(0, "Story 1"),
            Story(1, "Story 2"),
            Story(2, "Story 3"),
            Story(3, "Story 4"),
            Story(4, "Story 5"),
        )
    }

    override suspend fun getAllRoutes(): List<Route> {
        return listOf(
            Route("Маршрут номер 1")
        )
    }
}