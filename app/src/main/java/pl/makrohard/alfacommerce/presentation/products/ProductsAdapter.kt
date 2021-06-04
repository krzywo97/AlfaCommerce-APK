package pl.makrohard.alfacommerce.presentation.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.databinding.ProductTileBinding
import pl.makrohard.alfacommerce.domain.model.Product

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    var products: List<Product>? = null
    var onClickListener: ((product: Product) -> Unit)? = null

    inner class ProductViewHolder(
        private val binding: ProductTileBinding,
        private val onClickListener: ((product: Product) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.card.setOnClickListener {
                onClickListener?.invoke(product)
            }

            if (product.photos.isNotEmpty()) {
                Glide.with(binding.root)
                    .load(product.photos[0].url)
                    .placeholder(R.drawable.loading)
                    .fallback(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.image)
            }
            binding.name.text = product.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductTileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products!![holder.adapterPosition]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products?.size ?: 0
    }
}