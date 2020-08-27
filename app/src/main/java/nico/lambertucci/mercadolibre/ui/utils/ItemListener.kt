package nico.lambertucci.mercadolibre.ui.utils

import android.view.View
/**
 * @author Nicolas Lambertucci
 */
interface ItemListener {

    /**
     * Esta funcion es para poder tener un listener y que cuando selecciones un item te ayude a
     * navegar
     * @param item es un item puntual, es decir, el producto seleccionado de la lista
     */
    fun onClick(v: View, item: Int)
}