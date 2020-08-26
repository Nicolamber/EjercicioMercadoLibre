package nico.lambertucci.mercadolibre.domain.data

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city_id")
    val cityId: String,
    @SerializedName("city_name")
    val cityName: String,
    @SerializedName("state_id")
    val stateId: String,
    @SerializedName("state_name")
    val stateName: String
)