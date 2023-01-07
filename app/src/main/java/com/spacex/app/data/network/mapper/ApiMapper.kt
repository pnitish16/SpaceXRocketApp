package com.spacex.app.data.network.mapper

import com.spacex.app.data.network.model.ApiRocketDetail
import com.spacex.app.data.network.model.ApiRocketItem
import com.spacex.app.domain.model.Rocket
import com.spacex.app.domain.model.RocketDetail

interface ApiMapper {

  fun mapApiRocketItemToDomain(apiRocketItem: ApiRocketItem): Rocket

  fun mapApiRocketDetailToDomain(apiRocketDetail: ApiRocketDetail): RocketDetail
}