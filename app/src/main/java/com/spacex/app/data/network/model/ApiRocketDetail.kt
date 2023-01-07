package com.spacex.app.data.network.model

import com.squareup.moshi.Json

data class ApiRocketDetail(

	@Json(name="second_stage")
	val secondStage: SecondStage? = null,

	@Json(name="country")
	val country: String? = null,

	@Json(name="mass")
	val mass: Mass? = null,

	@Json(name="active")
	val active: Boolean? = null,

	@Json(name="cost_per_launch")
	val costPerLaunch: Int? = null,

	@Json(name="description")
	val description: String? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="payload_weights")
	val payloadWeights: List<PayloadWeightsItem?>? = null,

	@Json(name="first_flight")
	val firstFlight: String? = null,

	@Json(name="landing_legs")
	val landingLegs: LandingLegs? = null,

	@Json(name="diameter")
	val diameter: Diameter? = null,

	@Json(name="flickr_images")
	val flickrImages: List<String?>? = null,

	@Json(name="first_stage")
	val firstStage: FirstStage? = null,

	@Json(name="engines")
	val engines: Engines? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="stages")
	val stages: Int? = null,

	@Json(name="boosters")
	val boosters: Int? = null,

	@Json(name="company")
	val company: String? = null,

	@Json(name="success_rate_pct")
	val successRatePct: Int? = null,

	@Json(name="wikipedia")
	val wikipedia: String? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="height")
	val height: Height? = null
)
