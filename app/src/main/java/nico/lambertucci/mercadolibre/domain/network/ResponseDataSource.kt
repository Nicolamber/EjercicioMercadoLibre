package nico.lambertucci.mercadolibre.domain.network

import androidx.lifecycle.LiveData
import nico.lambertucci.mercadolibre.domain.data.ProductResponse

interface ResponseDataSource {

    val productListResult: LiveData<ProductResponse>

    suspend fun fetchResponse(product: String)
}