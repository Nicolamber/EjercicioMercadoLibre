package nico.lambertucci.mercadolibre.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nico.lambertucci.mercadolibre.domain.data.ProductResponse
import nico.lambertucci.mercadolibre.domain.network.DATASOURCE_TAG
import nico.lambertucci.mercadolibre.domain.repository.MeliRepository

class ResultViewModel(private val repository: MeliRepository) : ViewModel() {

    suspend fun search(product: String): LiveData<ProductResponse>? {
        return repository.getProduct(product)
    }
}