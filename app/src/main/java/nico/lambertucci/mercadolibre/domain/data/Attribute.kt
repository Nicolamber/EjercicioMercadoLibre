package nico.lambertucci.mercadolibre.domain.data

import com.google.gson.annotations.SerializedName

data class Attribute(
    @SerializedName("attribute_group_id")
    val attributeGroupId: String,
    @SerializedName("attribute_group_name")
    val attributeGroupName: String,
    val id: String,
    val name: String,
    val source: Long,
    @SerializedName("value_id")
    val valueId: String,
    @SerializedName("value_name")
    val valueName: String,
    @SerializedName("value_struct")
    val valueStruct: Any,
    val values: List<ValueXX>
)