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

    @GET("stories")
    suspend fun loadAllStories(): List<Story>

    @GET("routes")
    suspend fun loadAllRoutes(): List<Route>

    @GET("routes/{id}")
    suspend fun loadStoryById(@Path("id") id: Int): Story
}

//class ApiImpl : Api {
//    override suspend fun loadAllEvents(): List<Event> {
//        val response = URL("${BASE_URL}events").readText()
//        return Json.decodeFromString(response)
//    }
//
//    override suspend fun loadAllStories(): List<Story> {
//        val response = URL("${BASE_URL}stories").readText()
//        return Json.decodeFromString(response)
//    }
//
//    override suspend fun loadAllRoutes(): List<Route> {
//        val response = URL("${BASE_URL}routes").readText()
//        return Json.decodeFromString(response)
//    }
//
//    override suspend fun loadStoryById(id: Int): Story {
//        val response = URL("${BASE_URL}stories/$id/")
//        return Json.decodeFromString(response.toString())
//    }
//}

object ApiImpl {
    val retrofitService : Api by lazy {
        retrofit.create(Api::class.java)
    }
}