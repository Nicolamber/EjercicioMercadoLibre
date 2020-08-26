package nico.lambertucci.mercadolibre.domain.network

import kotlinx.coroutines.Deferred
import nico.lambertucci.mercadolibre.domain.data.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    fun getProductsAsync(@Query("q")product: String): Deferred<ProductResponse>
}