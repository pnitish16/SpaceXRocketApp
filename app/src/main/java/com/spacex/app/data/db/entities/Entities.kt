package com.spacex.app.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rocket_details_table")
data class DbRocketDetails(
    val name: String,
    val flickerImages: List<String>,
    val activeStatus: Boolean,
    val costPerLaunch: Int,
    val successRatePercent: Int,
    val description: String,
    val wikipediaLink: String,
    val height: Double,
    val diameter: Double,
    @PrimaryKey val id: String
) {
    companion object {
        val EMPTY = DbRocketDetails("", emptyList(), false, 0, 0, "", "", 0.0, 0.0, "")
    }
}

@Entity(tableName = "rockets_table")
data class DbRocket(
    @PrimaryKey val id: String,
    val name: String,
    val country: String,
    val enginesCount: Int,
    val flickerImage: String,
)