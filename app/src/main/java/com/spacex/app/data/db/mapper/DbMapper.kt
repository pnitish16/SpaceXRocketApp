package com.spacex.app.data.db.mapper

import com.spacex.app.data.db.entities.DbRocket
import com.spacex.app.data.db.entities.DbRocketDetails
import com.spacex.app.domain.model.Rocket
import com.spacex.app.domain.model.RocketDetail

interface DbMapper {

  fun mapDomainRocketDetailsToDb(rocketDetail: RocketDetail): DbRocketDetails

  fun mapDbRocketDetailsToDomain(rocketDetails: DbRocketDetails): RocketDetail

  fun mapDomainRocketsToDb(rockets: List<Rocket>): List<DbRocket>

  fun mapDbRocketsToDomain(rockets: List<DbRocket>): List<Rocket>
}