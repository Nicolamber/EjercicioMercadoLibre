package nico.lambertucci.mercadolibre.domain.network

import kotlinx.coroutines.Deferred
import nico.lambertucci.mercadolibre.domain.data.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Nicolas Lambertucci
 * Interface con la llamada a la API.
 */
interface ApiService {
    @GET("search")
    fun getProductsAsync(@Query("q")product: String): Deferred<ProductResponse>
}