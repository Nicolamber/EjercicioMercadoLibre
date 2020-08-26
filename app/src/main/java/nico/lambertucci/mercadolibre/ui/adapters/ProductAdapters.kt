package nico.lambertucci.mercadolibre.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import nico.lambertucci.mercadolibre.R
import nico.lambertucci.mercadolibre.domain.data.Result
import nico.lambertucci.mercadolibre.ui.utils.ItemListener
import java.math.BigDecimal
import java.math.RoundingMode
class ProductAdapters(
    private val resultList: List<Result>,
    private val itemListener: ItemListener
) : RecyclerView.Adapter<ProductAdapters.ViewHolder>() {

     lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapters.ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.product_item, parent, false),
            itemListener
        )
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = resultList[position]
        holder.bindUI(item, context)

    }

    class ViewHolder(itemView: View, private var listener: ItemListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val itemTitle: TextView = itemView.findViewById(R.id.productTitle)
        private val itemPrice: TextView = itemView.findViewById(R.id.productPrice)
        private val productImage: ImageView = itemView.findViewById(R.id.productImage)

        init {
            itemView.setOnClickListener(this)
        }

        fun bindUI(item: Result,context: Context) {
            itemTitle.text = item.title
            itemPrice.text = parsePrice(item.price)

            //La api las devuelve con htpp y la libreria las necesita https
            val image = item.thumbnail.replace("http","https")
            Glide.with(context)
                .load(image)
                .centerCrop()
                    //Mientras se cargan las imagenes aparece el logo de meli
                .placeholder(R.drawable.meli)
                    //En caso de que no tenga imagen o haya un error, le seteo esa imagen
                .error(R.drawable.noimage)
                .into(productImage)

        }

        private fun parsePrice(price: Double): String {
            val parsedPrice = BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN)
            return "$${parsedPrice}"
        }

        override fun onClick(view: View) {
            this.listener.onClick(view,adapterPosition)
        }
    }




}