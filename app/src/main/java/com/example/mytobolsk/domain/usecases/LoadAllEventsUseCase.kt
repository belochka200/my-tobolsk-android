package com.example.mytobolsk.domain.usecases

import com.example.mytobolsk.data.network.ApiImpl
import com.example.mytobolsk.domain.models.Event

interface LoadAllEventsUseCase {
    suspend fun getAllEvent(): List<Event>
}

class LoadAllEventUseCaseImpl(private val api: ApiImpl = ApiImpl()) : LoadAllEventsUseCase {
    override suspend fun getAllEvent(): List<Event> {
        return api.getAllEvent().map {
            Event(
                id = it.id,
                title = it.title
            )
        }
    }
}