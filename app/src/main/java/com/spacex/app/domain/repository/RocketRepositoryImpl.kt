package com.spacex.app.domain.repository

import android.util.Log
import com.spacex.app.data.db.dao.RocketDao
import com.spacex.app.data.db.mapper.DbMapper
import com.spacex.app.data.network.client.RocketApiClient
import com.spacex.app.data.network.mapper.ApiMapper
import com.spacex.app.domain.model.Rocket
import com.spacex.app.domain.model.RocketDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RocketRepositoryImpl(
    private val rocketApiClient: RocketApiClient,
    private val apiMapper: ApiMapper,
    private val backgroundDispatcher: CoroutineDispatcher,
    private val rocketDao: RocketDao,
    private val dbMapper: DbMapper
) : RocketRepository {
    override suspend fun fetchRocketDetails(rocketId: String): RocketDetail? = withContext(backgroundDispatcher) {
        val rocketDetail = try {
            apiMapper.mapApiRocketDetailToDomain(
                rocketApiClient.getRocketDetail(rocketId)
            )
        } catch (error: Throwable) {
            null
        }

        if (rocketDetail != null) {
            rocketDao.insertRocketDetails(dbMapper.mapDomainRocketDetailsToDb(rocketDetail))
        }

        rocketDetail
    }

    override suspend fun getRockets() = withContext(backgroundDispatcher) {
        val rocketsList = try {
            rocketApiClient.getRockets()
                .map(apiMapper::mapApiRocketItemToDomain)
        } catch (error: Throwable) {
            Log.d("exception:", error.message.toString())
            emptyList<Rocket>()
        }

        if (rocketsList.isNotEmpty()) {
            rocketDao.insertAllRockets(dbMapper.mapDomainRocketsToDb(rocketsList))
        }

        rocketsList
    }

    override fun getRocketsFromDb() =
        rocketDao.getRockets()
            .map { dbMapper.mapDbRocketsToDomain(it) }

    override fun getRocketDetailFromDb(rocketId: String) =
        rocketDao.getRocketDetail(rocketId).filterNotNull()
            .map {
                dbMapper.mapDbRocketDetailsToDomain(it)
            }
}