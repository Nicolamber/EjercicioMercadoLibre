package nico.lambertucci.mercadolibre.di

import androidx.lifecycle.ViewModelProvider
import nico.lambertucci.mercadolibre.domain.network.ResponseDataSourceImpl
import nico.lambertucci.mercadolibre.domain.repository.MeliRepository
import nico.lambertucci.mercadolibre.domain.repository.MeliRepositoryImpl
import nico.lambertucci.mercadolibre.ui.viewmodel.ViewModelFactory

/**
 * @author Nicolas Lambertucci
 *
 * Este objeto lo que hace es proveer instancias tanto de del ViewModelFactory como
 * de los dataSource y asi implementar una injeccion de dependencias sin uso de librerias
 * externas.
 */
object Injection {

    private val productDataSource = ResponseDataSourceImpl()
    private val productRepository = MeliRepositoryImpl()
    private val meliViewModel = ViewModelFactory(productRepository)

    fun getRepository(): MeliRepository{
        return productRepository
    }

    fun getDataSource() = productDataSource

    fun getViewModelFactory(): ViewModelProvider.Factory{
        return meliViewModel
    }
}