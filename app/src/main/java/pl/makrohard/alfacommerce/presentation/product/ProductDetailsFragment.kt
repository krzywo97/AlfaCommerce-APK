package pl.makrohard.alfacommerce.presentation.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import pl.makrohard.alfacommerce.databinding.ProductDetailsFragmentBinding
import pl.makrohard.alfacommerce.util.TextUtils

class ProductDetailsFragment : Fragment() {
    companion object {
        const val PRODUCT_ID = "productId"

        fun newInstance(productId: Int) = ProductDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(PRODUCT_ID, productId)
            }
        }
    }

    private lateinit var viewBinding: ProductDetailsFragmentBinding
    private val viewModel: ProductDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = ProductDetailsFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getInt(PRODUCT_ID)!!
        viewModel.fetchProductDetails(productId)

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