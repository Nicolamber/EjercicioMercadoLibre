package nico.lambertucci.mercadolibre.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkConnection(){

     fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

}