package com.example.mytobolsk.domain.models

data class Route(
    val id: Int,
    val title: String,
    val describe: String
)

fun List<Route>.asUiModel(): List<com.example.mytobolsk.ui.models.Route> {
    return map {
        com.example.mytobolsk.ui.models.Route(
            id = it.id,
            title = it.title,
            describe = it.describe
        )
    }
}