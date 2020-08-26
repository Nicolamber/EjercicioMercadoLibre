package nico.lambertucci.mercadolibre.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nico.lambertucci.mercadolibre.di.Injection
import nico.lambertucci.mercadolibre.domain.data.ProductResponse
import nico.lambertucci.mercadolibre.domain.network.DATASOURCE_TAG

class MeliRepositoryImpl() : MeliRepository {

    private val resultProductResponse = MutableLiveData<ProductResponse>()
    private val dataSource = Injection.getDataSource()

    init {
        dataSource.productListResult.observeForever{
            resultProductResponse.postValue(it)
        }
    }

    override suspend fun getProduct(product: String): LiveData<ProductResponse> {

        return withContext(Dispatchers.IO) {
            getResultData(product)
            Log.i(DATASOURCE_TAG,"Retrieved products from API")
            return@withContext resultProductResponse
        }
    }

    private suspend fun getResultData(product: String) {

        dataSource.fetchResponse(product)
    }

}