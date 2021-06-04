package pl.makrohard.alfacommerce.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.databinding.GalleryItemBinding
import pl.makrohard.alfacommerce.model.Photo

class GalleryAdapter(var photos: List<Photo>) :
    RecyclerView.Adapter<GalleryAdapter.GalleryItemViewHolder>() {

    inner class GalleryItemViewHolder(private val viewBinding: GalleryItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(photo: Photo) {
            Glide.with(viewBinding.image)
                .load(photo.url)
                .placeholder(R.drawable.loading)
                .fallback(R.drawable.no_image)
                .error(R.drawable.no_image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(viewBinding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder {
        val binding = GalleryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return GalleryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {
        holder.bind(photos[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return photos.size
    }
}