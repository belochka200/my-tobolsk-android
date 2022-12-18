package com.example.mytobolsk.data.models

data class Route(
    val id: Int,
    val title: String,
    val describe: String
)

fun List<Route>.asDomainModel(): List<com.example.mytobolsk.domain.models.Route> {
    return map {
        com.example.mytobolsk.domain.models.Route(
            id = it.id,
            title = it.title,
            describe = it.describe
        )
    }
}