package com.spacex.app.data.db.mapper

import com.spacex.app.data.db.entities.DbRocket
import com.spacex.app.data.db.entities.DbRocketDetails
import com.spacex.app.domain.model.Rocket
import com.spacex.app.domain.model.RocketDetail

class DbMapperImpl : DbMapper {
    override fun mapDomainRocketDetailsToDb(rocketDetail: RocketDetail): DbRocketDetails {
        return with(rocketDetail) {
            DbRocketDetails(
                name,
                flickerImages,
                activeStatus,
                costPerLaunch,
                successRatePercent,
                description,
                wikipediaLink,
                height,
                diameter,
                id
            )
        }
    }

    override fun mapDbRocketDetailsToDomain(rocketDetails: DbRocketDetails): RocketDetail {
        return with(rocketDetails) {
            RocketDetail(
                name,
                flickerImages,
                activeStatus,
                costPerLaunch,
                successRatePercent,
                description,
                wikipediaLink,
                height,
                diameter,
                id
            )
        }
    }

    override fun mapDomainRocketsToDb(rockets: List<Rocket>): List<DbRocket> {
        return rockets.map {
            with(it) {
                DbRocket(
                    id, name, country, enginesCount, flickerImage
                )
            }
        }
    }

    override fun mapDbRocketsToDomain(rockets: List<DbRocket>): List<Rocket> {
        return rockets.map {
            with(it) {
                Rocket(
                    id, name, country, enginesCount, flickerImage
                )
            }
        }
    }

}
