package nico.lambertucci.mercadolibre.domain.data

data class Filter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<ValueX>
)