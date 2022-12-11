package com.example.mytobolsk.data.network

import com.example.mytobolsk.data.models.Event
import com.example.mytobolsk.data.models.Route
import com.example.mytobolsk.data.models.Story
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_VERSION = 1
private const val BASE_URL = "http://192.168.1.137:8000/api/v${API_VERSION}/"

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface Api {
    @GET("events")
    suspend fun loadAllEvents(): List<Event>

    @GET("all stories")
    suspend fun loadAllStories(): List<Story>

    @GET("routes")
    suspend fun loadAllRoutes(): List<Route>

    @GET("all stories/{id}")
    suspend fun loadStoryById(@Path("id") id: Int): Story
}

object ApiImpl {
    val retrofitService : Api by lazy {
        retrofit.create(Api::class.java)
    }
}