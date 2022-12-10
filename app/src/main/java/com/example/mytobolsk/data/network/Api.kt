package com.example.mytobolsk.data.network

import com.example.mytobolsk.data.models.Event
import com.example.mytobolsk.data.models.Route
import com.example.mytobolsk.data.models.Story
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL

private const val API_VERSION = 1
private const val BASE_URL = "http://192.168.1.137:8000/api/v${API_VERSION}/"

interface Api {
    suspend fun loadAllEvents(): List<Event>
    suspend fun loadAllStories(): List<Story>
    suspend fun loadAllRoutes(): List<Route>
    suspend fun loadStoryById(id: Int): Story
}

class ApiImpl : Api {
    override suspend fun loadAllEvents(): List<Event> {
        val response = URL("${BASE_URL}events").readText()
        return Json.decodeFromString(response)
    }

    override suspend fun loadAllStories(): List<Story> {
        val response = URL("${BASE_URL}stories").readText()
        return Json.decodeFromString(response)
    }

    override suspend fun loadAllRoutes(): List<Route> {
        val response = URL("${BASE_URL}routes").readText()
        return Json.decodeFromString(response)
    }

    override suspend fun loadStoryById(id: Int): Story {
        val response = URL("${BASE_URL}stories/$id/")
        return Json.decodeFromString(response.toString())
    }
}