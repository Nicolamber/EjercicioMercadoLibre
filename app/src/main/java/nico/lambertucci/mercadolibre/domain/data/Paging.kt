package nico.lambertucci.mercadolibre.domain.data

import com.google.gson.annotations.SerializedName

data class Paging(
    val limit: Int,
    val offset: Int,
    @SerializedName("primary_results")
    val primaryResults: Int,
    val total: Int
)