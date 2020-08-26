package nico.lambertucci.mercadolibre.domain.repository

import androidx.lifecycle.LiveData
import nico.lambertucci.mercadolibre.domain.data.ProductResponse


interface MeliRepository {

    suspend fun getProduct(product: String): LiveData<ProductResponse>
}