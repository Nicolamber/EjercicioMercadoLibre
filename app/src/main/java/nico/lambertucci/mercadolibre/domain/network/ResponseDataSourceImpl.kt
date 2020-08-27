package nico.lambertucci.mercadolibre.domain.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nico.lambertucci.mercadolibre.domain.data.ProductResponse
import java.io.IOException

/**
 * @author Nicolas Lambertucci
 * Implementacion del dataSource, aca se hace la llamada a la API  de MeLi para recuperar un
 * listado de productos acorde a la query pasada.
 */
const val DATASOURCE_TAG = "dataLog"
class ResponseDataSourceImpl : ResponseDataSource {

    private val apiConnection = RetrofitBuilder.apiService

    private val serviceResult = MutableLiveData<ProductResponse>()

    override val productListResult: LiveData<ProductResponse>
        get() = serviceResult

    /**
     * Esta funcion lo que hace es llamar a getProductsAsync y guardar en el LiveData
     * el resultado devuelto por la API.
     * Si hubiese un error lo podemos ver por consola.
     */
    override suspend fun fetchResponse(product: String) {
        try {
            val productResult: ProductResponse = apiConnection.getProductsAsync(product).await()
            serviceResult.postValue(productResult)
            Log.i(DATASOURCE_TAG, "Products retrieved!")
        } catch (e: IOException) {
            Log.e(DATASOURCE_TAG, "Error during fetching response")
        }
    }
}