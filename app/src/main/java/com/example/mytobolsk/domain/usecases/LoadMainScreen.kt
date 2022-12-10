package com.example.mytobolsk.domain.usecases

import com.example.mytobolsk.data.network.ApiImpl
import com.example.mytobolsk.domain.models.Event
import com.example.mytobolsk.domain.models.Route
import com.example.mytobolsk.domain.models.Story

interface LoadMainScreen {
    suspend fun getAllEvents(): List<Event>
    suspend fun getAllRoutes(): List<Route>
    suspend fun getAllStories(): List<Story>
}

class LoadMainScreenImpl(private val api: ApiImpl = ApiImpl()) : LoadMainScreen {
    override suspend fun getAllEvents(): List<Event> {
        return api.loadAllEvents().map {
            Event(
                id = it.id,
                title = it.title,
                describe = it.describe,
                date = it.date,
                time = it.time,
                place = it.place
            )
        }
    }

    override suspend fun getAllRoutes(): List<Route> {
        return api.loadAllRoutes().map {
            Route(
                id = it.id,
                title = it.title,
                describe = it.describe
            )
        }
    }

    override suspend fun getAllStories(): List<Story> {
        return api.loadAllStories().map {
            Story(
                id = it.id,
                title = it.title,
                describe = it.describe
            )
        }
    }
}