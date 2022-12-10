package com.example.mytobolsk.domain.usecases

import com.example.mytobolsk.data.network.ApiImpl
import com.example.mytobolsk.domain.models.Story

interface LoadStoryById {
    suspend fun getStory(id: Int): Story
}

class LoadStoryByIdImpl(private val api: ApiImpl = ApiImpl()) : LoadStoryById {
    override suspend fun getStory(id: Int): Story {
        val response = api.loadStoryById(id)
        return Story(
            id = response.id,
            title = response.title,
            describe = response.describe
        )
    }
}