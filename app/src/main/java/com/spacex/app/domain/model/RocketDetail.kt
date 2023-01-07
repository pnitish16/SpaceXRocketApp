package com.spacex.app.domain.model

data class RocketDetail(
    val name: String,
    val flickerImages: List<String>,
    val activeStatus: Boolean,
    val costPerLaunch: Int,
    val successRatePercent: Int,
    val description:String,
    val wikipediaLink: String,
    val height: Double,
    val diameter: Double,
    val id: String,
)
