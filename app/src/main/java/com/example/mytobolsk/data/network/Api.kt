package com.example.mytobolsk.data.network

import com.example.mytobolsk.data.models.Event
import com.example.mytobolsk.data.models.Route
import com.example.mytobolsk.data.models.Story
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URL

private const val BASE_URL = "http://192.168.1.137:8000/api/"

interface Api {
    suspend fun loadAllEvents(): List<Event>
    suspend fun loadAllStories(): List<Story>
    suspend fun loadAllRoutes(): List<Route>
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
}