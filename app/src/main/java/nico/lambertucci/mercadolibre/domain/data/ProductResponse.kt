package nico.lambertucci.mercadolibre.domain.data

import com.google.gson.annotations.SerializedName

data class ProductResponse(

    val availableFilters: List<AvailableFilter>,
    val availableSorts: List<AvailableSort>,
    val filters: List<Filter>,
    val paging: Paging,
    val query: String,
    @SerializedName("related_results")
    val relatedResults: List<Any>,
    val results: List<Result>,
    @SerializedName("secondary_results")
    val secondaryResults: List<Any>,
    @SerializedName("site_id")
    val siteId: String,
    val sort: Sort
)