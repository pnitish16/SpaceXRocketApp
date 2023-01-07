package com.spacex.app.data.network.client

import com.spacex.app.data.network.model.ApiRocketDetail
import com.spacex.app.data.network.model.ApiRocketItem
import retrofit2.http.GET
import retrofit2.http.Path

interface RocketApiClient {

  @GET("rockets")
  suspend fun getRockets(): List<ApiRocketItem>

  @GET("rockets/{rocket_id}")
  suspend fun getRocketDetail(@Path("rocket_id") rocket_id: String): ApiRocketDetail
}