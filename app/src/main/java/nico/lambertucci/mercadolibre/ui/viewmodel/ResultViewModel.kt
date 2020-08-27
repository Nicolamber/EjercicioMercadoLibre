package nico.lambertucci.mercadolibre.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import nico.lambertucci.mercadolibre.domain.data.ProductResponse
import nico.lambertucci.mercadolibre.domain.repository.MeliRepository

/**
 * @author Nicolas Lambertucci
 * ViewModel asociado a la vista principal de la app.
 */
class ResultViewModel(private val repository: MeliRepository) : ViewModel() {

    /**
     * funcion que consume los servicios del repo injectado para obtener la lista de productos
     * @param product producto a buscar
     * @return LiveData<ProductResponse> Lista de productos que puede venir nula
     */
    suspend fun search(product: String): LiveData<ProductResponse> {
        return repository.getProduct(product)
    }
}