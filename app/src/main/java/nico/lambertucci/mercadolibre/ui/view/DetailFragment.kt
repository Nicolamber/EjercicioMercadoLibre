package nico.lambertucci.mercadolibre.ui.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import nico.lambertucci.mercadolibre.R
import nico.lambertucci.mercadolibre.domain.data.Result
import java.math.BigDecimal
import java.math.RoundingMode
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import nico.lambertucci.mercadolibre.ui.utils.LoadingBar

/**
 * @author Nicolas Lambertucci
 * Vista que muestra un producto en detalle
 */
class DetailFragment : Fragment(), LoadingBar {

    private lateinit var detailedProduct: Result

    private lateinit var detailToolbar: Toolbar
    private lateinit var productTitle: TextView
    private lateinit var productPrice: TextView
    private lateinit var acceptMercadoPago: TextView
    private lateinit var availableProducts: TextView
    private lateinit var imageProduct: ImageView
    private lateinit var loadingBar: ProgressBar
    private lateinit var loadingText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.detail_fragment, container, false)
        productTitle = view.findViewById(R.id.titleDetail)
        productPrice = view.findViewById(R.id.priceDetail)
        acceptMercadoPago = view.findViewById(R.id.acceptMP)
        availableProducts = view.findViewById(R.id.availableProd)
        imageProduct = view.findViewById(R.id.detailImage)
        detailToolbar = view.findViewById(R.id.detailToolbar)
        loadingBar = view.findViewById(R.id.loadingBar)
        loadingText = view.findViewById(R.id.loadingDetailText)

        (activity as AppCompatActivity).apply {
            setSupportActionBar(detailToolbar)
            setHasOptionsMenu(true)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detailedProduct = selectedProduct

        // Mientras se popula la vista con los atributos del producto, aparece una barra de carga.
        showLoadingBar()

        // Inflamos el toolbar para tener la navegacion hacia atras y el titulo.
        setupToolbar(detailToolbar)
        setupView(detailedProduct)
    }

    /**
     * Funcion que se encarga de setear a cada atributo el valor correspondiente y armar la vista
     * del detalle de un producto previamente seleccionado por el usuario.
     * @param item producto seleccionado.
     */
    private fun setupView(item: Result) {
        productTitle.text = item.title

        if (item.acceptsMercadopago) {
            acceptMercadoPago.text = getString(R.string.acceptMPRes)
        } else {
            acceptMercadoPago.text = getString(R.string.dontAcceptMPRes)
        }

        availableProducts.text = getString(R.string.availableQuantity) + item.availableQuantity

        productPrice.text = setPrice(item.price)

        /*
        * [WORKAROUND]
        * La api las devuelve con htpp y la libreria las necesita https, tambien intercepte unas
        * urls que eran http://http2.mlstatic/path/imagen.jpg y cuando intentas acceder te dice
        * que la pagina no existe por eso algunos productos salen como sin imagen
        */
        val image = item.thumbnail.replace("http", "https")

        Glide.with(requireContext())
            .load(image)
            .centerCrop()
            //Mientras se cargan las imagenes aparece el logo de meli
            .placeholder(R.drawable.meli)
            //En caso de que no tenga imagen o haya un error, le seteo esa imagen
            .error(R.drawable.noimage)
            .into(imageProduct)
        hideLoadingBar()
    }


    /**
     * Esta funcion lo que hace es parcear el precio para que se vea mas como aparece en la app de Meli
     * dejando dos decimales despues del precio.
     * @param price precio del producto
     * @return String
     */
    private fun setPrice(price: Double): String {
        val productPrice = BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN)
        return "$${productPrice}"
    }

    /**
     * Dibujamos la vista del toolbar que va a tener un navigate bakc y el titulo de la vista
     */
    private fun setupToolbar(toolbar: Toolbar) {

        toolbar.apply {
            title = context.getString(R.string.productDetailTitle)
            setHasOptionsMenu(true)
            setNavigationIcon(R.drawable.ic_arrow_back_24)
            setNavigationOnClickListener {
                findNavController().navigate(R.id.resultFragment)
            }
        }
    }

    override fun hideLoadingBar() {
        loadingBar.visibility = View.GONE
        loadingText.visibility = View.GONE
    }

    override fun showLoadingBar() {
        loadingBar.visibility = View.VISIBLE
        loadingText.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_item_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * metodo agregado al final, contiene la opcion de salir de la app.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.closeApp ->{ requireActivity().finish()}
        }
        return super.onOptionsItemSelected(item)
    }
}