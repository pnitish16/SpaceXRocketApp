package com.spacex.app.data.network.mapper

import com.spacex.app.data.network.model.ApiRocketDetail
import com.spacex.app.data.network.model.ApiRocketItem
import com.spacex.app.domain.model.Rocket
import com.spacex.app.domain.model.RocketDetail

class ApiMapperImpl : ApiMapper {
    override fun mapApiRocketItemToDomain(apiRocketItem: ApiRocketItem): Rocket {
        return with(apiRocketItem) {
            var flickerImage = String()
            apiRocketItem.flickrImages?.let {
                if (it.isNotEmpty()) {
                    flickerImage = it.first().toString()
                }
            }
            Rocket(
                id = apiRocketItem.id ?: String(),
                name = apiRocketItem.name ?: String(),
                country = apiRocketItem.country ?: String(),
                enginesCount = apiRocketItem.engines?.let {
                    it.number ?: 0
                } ?: 0,
                flickerImage = flickerImage
            )
        }
    }

    override fun mapApiRocketDetailToDomain(apiRocketDetail: ApiRocketDetail): RocketDetail {
        return with(apiRocketDetail) {
            var flickerImages = emptyList<String>()
            apiRocketDetail.flickrImages?.let {
                flickerImages = it.filterNotNull()
            }
            RocketDetail(
                name = apiRocketDetail.name ?: String(),
                flickerImages = flickerImages,
                activeStatus = apiRocketDetail.active ?: false,
                costPerLaunch = apiRocketDetail.costPerLaunch ?: 0,
                successRatePercent = apiRocketDetail.successRatePct ?: 0,
                description = apiRocketDetail.description ?: String(),
                wikipediaLink = apiRocketDetail.wikipedia ?: String(),
                height = apiRocketDetail.height?.feet ?: 0.0,
                diameter = apiRocketDetail.diameter?.feet ?: 0.0,
                id = apiRocketDetail.id ?: String()
            )
        }
    }
}