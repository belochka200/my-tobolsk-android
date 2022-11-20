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
        return api.getAllEvent().map {
            Event(
                title = it.title!!
            )
        }
    }

    override suspend fun getAllRoutes(): List<Route> {
        return api.getAllRoutes().map {
            Route(
                title = it.title!!
            )
        }
    }

    override suspend fun getAllStories(): List<Story> {
        return api.getAllStories().map {
            Story(
                title = it.title!!
            )
        }
    }
}