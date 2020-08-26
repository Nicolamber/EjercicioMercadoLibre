package nico.lambertucci.mercadolibre.domain.data

data class AvailableFilter(
    val id: String,
    val name: String,
    val type: String,
    val values: List<Value>
)