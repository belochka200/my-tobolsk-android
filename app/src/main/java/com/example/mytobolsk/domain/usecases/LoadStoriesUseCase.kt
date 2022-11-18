package com.example.mytobolsk.domain.usecases

import com.example.mytobolsk.data.network.ApiImpl
import com.example.mytobolsk.domain.models.Story

interface LoadStoriesUseCase {
    suspend fun getAllStories(): List<Story>
}

class LoadStoriesUseCaseImpl(private val api: ApiImpl = ApiImpl()) : LoadStoriesUseCase {
    override suspend fun getAllStories(): List<Story> {
        return api.getAllStories().map {
            Story(
                title = it.title
            )
        }
    }
}