package com.spacex.app.domain.model

data class Rocket(
    val id: String,
    val name: String,
    val country: String,
    val enginesCount: Int,
    val flickerImage: String,
)