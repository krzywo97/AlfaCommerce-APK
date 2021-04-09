package pl.makrohard.alfacommerce.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.databinding.ProductTileBinding
import pl.makrohard.alfacommerce.model.Product

class ProductsAdapter(
    var products: List<Product>,
    private val onClickListener: (product: Product) -> Unit
) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(
        private val binding: ProductTileBinding,
        private val onClickListener: (product: Product) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.card.setOnClickListener {
                onClickListener.invoke(product)
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
        val product = products[holder.adapterPosition]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }
}