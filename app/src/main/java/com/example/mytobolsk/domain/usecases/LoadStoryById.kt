package com.example.mytobolsk.domain.usecases

import com.example.mytobolsk.data.network.ApiImpl
import com.example.mytobolsk.domain.models.Story

interface LoadStoryById {
    suspend fun getStory(id: Int): Story
}

class LoadStoryByIdImpl : LoadStoryById {
    override suspend fun getStory(id: Int): Story {
        val response = ApiImpl.retrofitService.loadStoryById(id)
        return Story(
            id = id,
            title = response.title,
            describe = response.describe
        )
    }
}