package com.example.mytobolsk.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mytobolsk.data.database.AppDatabase
import com.example.mytobolsk.data.models.asDomainModel
import com.example.mytobolsk.data.network.ApiImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(private val database: AppDatabase) {

    val videos: LiveData<List<com.example.mytobolsk.domain.models.Story>> =
        Transformations.map(database.storiesDao.getAll()) {
            it.asDomainModel()
        }

    suspend fun refreshStories() {
        withContext(Dispatchers.IO) {
            val stories = ApiImpl.retrofitService.loadAllStories()
            database.storiesDao.insertAll(stories)
        }
    }
}