package com.example.mytobolsk.data.network

import android.util.Log
import com.example.mytobolsk.data.models.Event
import com.example.mytobolsk.data.models.Route
import com.example.mytobolsk.data.models.Story
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

interface Api {
    suspend fun getAllEvent(): List<Event>
    suspend fun getAllStories(): List<Story>
    suspend fun getAllRoutes(): List<Route>
}

class ApiImpl : Api {
    override suspend fun getAllEvent(): List<Event> {
        return listOf(
            Event("Мероприятие 1")
        )
    }

    override suspend fun getAllStories(): List<Story> {
        return listOf(
            Story("Story 1"),
            Story("Story 2"),
            Story("Story 3"),
            Story("Story 4"),
            Story("Story 5"),
        )
    }

    override suspend fun getAllRoutes(): List<Route> {
//        val database = FirebaseDatabase.getInstance().getReference("routes")
//        val routesList: MutableList<Route> = mutableListOf()
//        database.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                for (route in snapshot.children) {
//                    routesList.add(route.getValue(Route::class.java)!!)
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {}
//        })
        return listOf(
            Route("Маршрут номер 1")
        )
    }
}