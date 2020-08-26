package nico.lambertucci.mercadolibre.domain.data

import com.google.gson.annotations.SerializedName

data class SellerAddress(
    @SerializedName("address_line")
    val addressLine: String,
    val city: City,
    val comment: String,
    val country: Country,
    val id: String,
    val latitude: String,
    val longitude: String,
    val state: State,
    @SerializedName("zip_code")
    val zipCode: String
)