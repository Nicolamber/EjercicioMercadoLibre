package nico.lambertucci.mercadolibre.domain.data

import com.google.gson.annotations.SerializedName

data class Seller(
    @SerializedName("car_dealer")
    val carDealer: Boolean,
    val id: Int,
    @SerializedName("power_seller_status")
    val powerSellerStatus: String,
    @SerializedName("real_estate_agency")
    val realEstateAgency: Boolean,
    val tags: List<Any>
)