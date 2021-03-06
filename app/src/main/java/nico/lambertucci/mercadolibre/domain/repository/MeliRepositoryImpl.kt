package nico.lambertucci.mercadolibre.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nico.lambertucci.mercadolibre.di.Injection
import nico.lambertucci.mercadolibre.domain.data.ProductResponse
import nico.lambertucci.mercadolibre.domain.network.DATASOURCE_TAG
/**
 * @author Nicolas Lambertucci
 * Implementacion de un repositorio para que si a futuro se desea consumir una API diferente o
 * almacenamiento local, desde aca podamos realizarlo (siguiendo MVVM)
 */
class MeliRepositoryImpl() : MeliRepository {

    private val resultProductResponse = MutableLiveData<ProductResponse>()
    private val dataSource = Injection.getDataSource()

    init {
        dataSource.productListResult.observeForever{
            resultProductResponse.postValue(it)
        }
    }

    /**
     * Esta funcion se encarga de retornar los productos que el usuario busca
     * @return resultProductResponse , lista de productos devuelta por la API
     * @param product query que contiene el producto que el usuario desea
     */
    override suspend fun getProduct(product: String): LiveData<ProductResponse> {

        return withContext(Dispatchers.IO) {
            getResultData(product)
            Log.i(DATASOURCE_TAG,"Retrieved products from API")
            return@withContext resultProductResponse
        }
    }

    /**
     * Comunicacion entre el repositorio y el dataSource que consume a la API
     * @param product query que contiene el producto buscado
     */
    private suspend fun getResultData(product: String) {

        dataSource.fetchResponse(product)
    }

}