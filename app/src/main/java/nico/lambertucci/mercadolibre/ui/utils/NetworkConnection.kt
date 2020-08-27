package nico.lambertucci.mercadolibre.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * @author Nicolas Lambertucci
 * Clase utilitaria para el chequeo de conexion a internet.
 */
class NetworkConnection(){

    /**
     * Funcion que se encarga de verificar si el dispositivo esta conectado a alguna red ya sea
     * WiFi, Datos moviles, etc.
     *
     * @return Boolean , True si esta conectado y False si no esta.
     */
     fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

}