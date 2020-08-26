package nico.lambertucci.mercadolibre.domain.data

import com.google.gson.annotations.SerializedName

data class Installments(
    val amount: Double,
    @SerializedName("currency_id")
    val currencyId: String,
    val quantity: Int,
    val rate: Double
)