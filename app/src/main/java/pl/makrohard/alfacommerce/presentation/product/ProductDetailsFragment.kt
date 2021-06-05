package pl.makrohard.alfacommerce.presentation.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import pl.makrohard.alfacommerce.databinding.ProductDetailsFragmentBinding
import pl.makrohard.alfacommerce.util.TextUtils

class ProductDetailsFragment : Fragment() {
    companion object {
        fun newInstance() = ProductDetailsFragment()
    }


    private lateinit var viewBinding: ProductDetailsFragmentBinding
    private val viewModel by sharedViewModel<ProductDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = ProductDetailsFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GalleryAdapter(emptyList())
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewBinding.gallery.adapter = adapter
        viewBinding.gallery.layoutManager = layoutManager

        viewModel.getProduct().observe(viewLifecycleOwner) {
            adapter.photos = it.photos
            adapter.notifyDataSetChanged()

            viewBinding.name.text = it.name
            viewBinding.price.text = TextUtils.formatPrice(it.price)
            viewBinding.color.text = it.color.name
            viewBinding.weight.text = it.weight.toString()
        }
    }
}