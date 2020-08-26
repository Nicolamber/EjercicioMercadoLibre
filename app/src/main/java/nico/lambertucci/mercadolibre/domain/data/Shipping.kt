package nico.lambertucci.mercadolibre.domain.data

import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("free_shipping")
    val freeShipping: Boolean,
    @SerializedName("logistic_type")
    val logisticType: String,
    val mode: String,
    @SerializedName("store_pick_up")
    val storePickUp: Boolean,
    val tags: List<String>
)