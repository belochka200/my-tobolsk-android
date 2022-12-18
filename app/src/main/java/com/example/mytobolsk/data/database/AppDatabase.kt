package com.example.mytobolsk.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mytobolsk.data.models.Story

@Dao
interface StoriesDao {
    @Query("SELECT * FROM story")
    fun getAll(): LiveData<List<Story>>

    @Insert
    fun insertAll(stories: List<Story>)
}

@Database(entities = [Story::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val storiesDao: StoriesDao
}

fun getDatabase(applicationContext: Context): RoomDatabase {
    return Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
}