package nico.lambertucci.mercadolibre.ui.view

import androidx.appcompat.widget.Toolbar
import android.os.Bundle
import android.text.Html.fromHtml
import android.view.*
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nico.lambertucci.mercadolibre.R
import nico.lambertucci.mercadolibre.di.Injection
import nico.lambertucci.mercadolibre.ui.viewmodel.ResultViewModel
import kotlinx.coroutines.launch
import nico.lambertucci.mercadolibre.domain.data.Result
import nico.lambertucci.mercadolibre.ui.adapters.ProductAdapters
import nico.lambertucci.mercadolibre.ui.utils.ItemListener
import nico.lambertucci.mercadolibre.ui.utils.LoadingBar
import nico.lambertucci.mercadolibre.ui.utils.NetworkConnection
import nico.lambertucci.mercadolibre.ui.utils.SearchUtils

lateinit var selectedProduct: Result
var globalQuery: String = ""

/**
 * @author Nicolas Lambertucci
 * vista principal de la app.
 */
class ResultFragment : Fragment(), SearchUtils, LoadingBar {

    private lateinit var viewModel: ResultViewModel
    private lateinit var toolbar: Toolbar

    private lateinit var progressBar: ProgressBar
    private lateinit var loadingText: TextView
    private lateinit var noSearchText: TextView
    private lateinit var logoImage: ImageView

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.result_fragment, container, false)
        toolbar = view.findViewById(R.id.resultToolbar)
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            setHasOptionsMenu(true)
        }

        progressBar = view.findViewById(R.id.resultProgressBar)
        loadingText = view.findViewById(R.id.loadingFragmentText)
        noSearchText = view.findViewById(R.id.nosearchs)
        logoImage = view.findViewById(R.id.melilogo)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            Injection.getViewModelFactory()
        ).get(ResultViewModel::class.java)

        //Primero chequeamos el estado de conexion del dispositivo y acorde a eso vemos que hacer
        if (NetworkConnection().isConnectedToInternet(requireContext())){
            hideLoadingBar()
            showNoSearches()
        }else{
            showNoInternetConnectionError()
            hideLoadingBar()
        }


    }

    override fun onResume() {
        super.onResume()
        /*Guardo la ultima busqueda en una query global para que si quiere volver del detalle
        tenga su ultima busqueda y no aparezca la ui vacia. */
        if (globalQuery.isNotEmpty()) {
            searchProduct(globalQuery)
        }else{
            showNoSearches()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()

        inflater.inflate(R.menu.search_item_menu, menu)
        val searchItem = menu.findItem(R.id.searchProduct)

        searchItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
        val searchView = searchItem?.actionView as SearchView

        searchView.backgroundTintList = resources.getColorStateList(R.color.primary_text)

        //workaround xq no encontraba como cambiarle el color a la fuente programaticamente
        searchView.queryHint = fromHtml("<font color = #000000>" + getString(R.string.search) + "</font>")


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchItem.collapseActionView()
                if (query != null) {
                    //Primero guardo la query por si recurro al onResume y despues busco.
                    globalQuery = query
                    searchProduct(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    /**
     * Este metodo se encarga de realizar la busqueda del producto elegido de manera asincrona
     * usando coroutinas y una vez obtenido el resultado dibuja la vista.
     * @param product producto que desea buscar el usuario
     */
    private fun searchProduct(product: String) = lifecycleScope.launch {
        hideNoSearches()
        showLoadingBar()
        viewManager = LinearLayoutManager(requireContext())
        viewModel.search(product).observe(viewLifecycleOwner, Observer {
            viewAdapter = ProductAdapters(it.results as ArrayList<Result>, object : ItemListener {
                override fun onClick(v: View, item: Int) {
                    selectedProduct = it.results[item]
                    findNavController().navigate(R.id.detailFragment)
                }

            })
            recyclerView =
                requireView().findViewById<RecyclerView>(R.id.productResultRecyclerView).apply {
                    setHasFixedSize(true)
                    addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            LinearLayoutManager.VERTICAL
                        )
                    )
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
        })
        hideLoadingBar()
    }

    override fun hideLoadingBar() {
        progressBar.visibility = View.GONE
        loadingText.visibility = View.GONE
    }

    override fun showLoadingBar() {
        progressBar.visibility = View.VISIBLE
        loadingText.visibility = View.VISIBLE
    }

    override fun showNoSearches() {
        noSearchText.visibility = View.VISIBLE
        logoImage.visibility = View.VISIBLE
    }

    override fun hideNoSearches() {
        noSearchText.visibility = View.GONE
        logoImage.visibility = View.GONE
    }

    override fun showNoInternetConnectionError() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.generic_error_title))
        builder.setMessage(getString(R.string.no_internet_message))
        builder.setPositiveButton(android.R.string.ok) { _, _ ->
            builder.show().dismiss()
        }
        hideNoSearches()
        showNoInternet()
        builder.show()
    }

    override fun showNoInternet() {
        noSearchText.text = getString(R.string.no_internet_ui_message)
        logoImage.visibility = View.VISIBLE
        noSearchText.visibility = View.VISIBLE
    }

}



