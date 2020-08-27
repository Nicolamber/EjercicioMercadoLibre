package nico.lambertucci.mercadolibre.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nico.lambertucci.mercadolibre.domain.repository.MeliRepository

class ViewModelFactory(private val repository: MeliRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass){
            ResultViewModel::class.java -> {
                ResultViewModel(this.repository) as T
            }
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}