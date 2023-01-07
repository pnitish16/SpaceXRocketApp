package com.spacex.app.data.network.model

import com.squareup.moshi.Json

data class LandingLegs(

	@Json(name="number")
	val number: Int? = null,

	@Json(name="material")
	val material: Any? = null
)

data class Engines(

	@Json(name="layout")
	val layout: String? = null,

	@Json(name="number")
	val number: Int? = null,

	@Json(name="propellant_1")
	val propellant1: String? = null,

	@Json(name="thrust_sea_level")
	val thrustSeaLevel: ThrustSeaLevel? = null,

	@Json(name="engine_loss_max")
	val engineLossMax: Int? = null,

	@Json(name="thrust_to_weight")
	val thrustToWeight: Double? = null,

	@Json(name="thrust_vacuum")
	val thrustVacuum: ThrustVacuum? = null,

	@Json(name="isp")
	val isp: Isp? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="version")
	val version: String? = null,

	@Json(name="propellant_2")
	val propellant2: String? = null
)

data class CompositeFairing(

	@Json(name="diameter")
	val diameter: Diameter? = null,

	@Json(name="height")
	val height: Height? = null
)

data class Thrust(

	@Json(name="lbf")
	val lbf: Int? = null,

	@Json(name="kN")
	val kN: Int? = null
)

data class Height(

	@Json(name="feet")
	val feet: Double? = null,

	@Json(name="meters")
	val meters: Double? = null
)

data class Diameter(

	@Json(name="feet")
	val feet: Double? = null,

	@Json(name="meters")
	val meters: Double? = null
)

data class ThrustSeaLevel(

	@Json(name="lbf")
	val lbf: Int? = null,

	@Json(name="kN")
	val kN: Int? = null
)

data class Isp(

	@Json(name="vacuum")
	val vacuum: Int? = null,

	@Json(name="sea_level")
	val seaLevel: Int? = null
)

data class PayloadWeightsItem(

	@Json(name="lb")
	val lb: Int? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="kg")
	val kg: Int? = null
)

data class SecondStage(

	@Json(name="fuel_amount_tons")
	val fuelAmountTons: Double? = null,

	@Json(name="payloads")
	val payloads: Payloads? = null,

	@Json(name="engines")
	val engines: Int? = null,

	@Json(name="burn_time_sec")
	val burnTimeSec: Int? = null,

	@Json(name="thrust")
	val thrust: Thrust? = null,

	@Json(name="reusable")
	val reusable: Boolean? = null
)

data class Mass(

	@Json(name="lb")
	val lb: Int? = null,

	@Json(name="kg")
	val kg: Int? = null
)

data class ThrustVacuum(

	@Json(name="lbf")
	val lbf: Int? = null,

	@Json(name="kN")
	val kN: Int? = null
)

data class FirstStage(

	@Json(name="thrust_sea_level")
	val thrustSeaLevel: ThrustSeaLevel? = null,

	@Json(name="fuel_amount_tons")
	val fuelAmountTons: Double? = null,

	@Json(name="thrust_vacuum")
	val thrustVacuum: ThrustVacuum? = null,

	@Json(name="engines")
	val engines: Int? = null,

	@Json(name="burn_time_sec")
	val burnTimeSec: Int? = null,

	@Json(name="reusable")
	val reusable: Boolean? = null
)

data class ApiRocketItem(

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

data class Payloads(

	@Json(name="composite_fairing")
	val compositeFairing: CompositeFairing? = null,

	@Json(name="option_1")
	val option1: String? = null
)
