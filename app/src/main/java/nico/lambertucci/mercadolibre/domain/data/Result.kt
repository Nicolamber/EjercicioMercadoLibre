package nico.lambertucci.mercadolibre.domain.data

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean,
    val address: Address,
    val attributes: List<Attribute>,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    @SerializedName("buying_mode")
    val buyingMode: String,
    @SerializedName("catalog_listing")
    val catalogListing: Boolean,
    @SerializedName("catalog_product_id")
    val catalogProductId: String,
    @SerializedName("category_id")
    val categoryId: String,
    val condition: String,
    @SerializedName("currency_id")
    val currencyId: String,
    @SerializedName("differential_pricing")
    val differentialPricing: DifferentialPricing,
    val id: String,
    val installments: Installments,
    @SerializedName("listing_type_id")
    val listingTypeId: String,
    @SerializedName("official_store_id")
    val officialStoreId: Int,
    @SerializedName("original_price")
    val originalPrice: Double,
    val permalink: String,
    val price: Double,
    val seller: Seller,
    @SerializedName("seller_address")
    val sellerAddress: SellerAddress,
    val shipping: Shipping,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("sold_quantity")
    val soldQuantity: Int,
    @SerializedName("stop_time")
    val stopTime: String,
    val tags: List<String>,
    val thumbnail: String,
    val title: String
)