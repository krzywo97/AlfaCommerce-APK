package pl.makrohard.alfacommerce.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pl.makrohard.alfacommerce.databinding.ProductTileBinding
import pl.makrohard.alfacommerce.model.Product

class ProductsAdapter(var products: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(private val binding: ProductTileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            if (product.photos.isNotEmpty()) {
                Glide.with(binding.root).load(product.photos[0].url).into(binding.image)
            }
            binding.name.text = product.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductTileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[holder.adapterPosition]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }
}