package com.spacex.app.domain.repository

import com.spacex.app.domain.model.Rocket
import com.spacex.app.domain.model.RocketDetail
import kotlinx.coroutines.flow.Flow

interface RocketRepository {

    suspend fun fetchRocketDetails(rocketId: String) : RocketDetail?

    suspend fun getRockets(): List<Rocket>

    fun getRocketsFromDb() : Flow<List<Rocket>>

    fun getRocketDetailFromDb(rocketId: String) : Flow<RocketDetail>
}