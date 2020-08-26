package nico.lambertucci.mercadolibre.domain.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nico.lambertucci.mercadolibre.domain.data.ProductResponse
import java.io.IOException

const val DATASOURCE_TAG = "dataLog"
class ResponseDataSourceImpl : ResponseDataSource {

    private val apiConnection = RetrofitBuilder.apiService

    private val serviceResult = MutableLiveData<ProductResponse>()

    override val productListResult: LiveData<ProductResponse>
        get() = serviceResult

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