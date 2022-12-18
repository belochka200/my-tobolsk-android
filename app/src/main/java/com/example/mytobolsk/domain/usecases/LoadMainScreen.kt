package com.example.mytobolsk.domain.usecases

import android.util.Log
import com.example.mytobolsk.data.models.asDomainModel
import com.example.mytobolsk.data.network.ApiImpl
import com.example.mytobolsk.domain.models.Event
import com.example.mytobolsk.domain.models.Route
import com.example.mytobolsk.domain.models.Story

interface LoadMainScreen {
    suspend fun getAllEvents(): List<Event>
    suspend fun getAllRoutes(): List<Route>
    suspend fun getAllStories(): List<Story>
}

class LoadMainScreenImpl : LoadMainScreen {

    override suspend fun getAllEvents(): List<Event> {
        return ApiImpl.retrofitService.loadAllEvents().asDomainModel()
    }

    override suspend fun getAllRoutes(): List<Route> {
        return ApiImpl.retrofitService.loadAllRoutes().asDomainModel()
    }

    override suspend fun getAllStories(): List<Story> {
        Log.d("API", ApiImpl.retrofitService.loadAllStories().toString())
        return ApiImpl.retrofitService.loadAllStories().asDomainModel()
    }
}