package pl.makrohard.alfacommerce.presentation.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.databinding.ProductsFragmentBinding
import pl.makrohard.alfacommerce.domain.model.Filters
import pl.makrohard.alfacommerce.domain.model.Product
import pl.makrohard.alfacommerce.presentation.home.MainNavigator
import pl.makrohard.alfacommerce.presentation.product.ProductDetailsViewModel

class ProductsFragment : Fragment() {
    companion object {
        private const val CATEGORY_ID = "category_id"

        fun newInstance(categoryId: Int): ProductsFragment {
            val fragment = ProductsFragment()
            val bundle = Bundle()
            bundle.putInt(CATEGORY_ID, categoryId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var viewBinding: ProductsFragmentBinding
    private val viewModel by viewModel<ProductsViewModel> {
        parametersOf(Filters())
    }
    private val productDetailsViewModel by sharedViewModel<ProductDetailsViewModel>()
    private val adapter: ProductsAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val category = arguments?.getInt(CATEGORY_ID) ?: -1

        viewModel.filters.category = category
        viewModel.fetchProducts(false)

        viewBinding = ProductsFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.onClickListener = this::onProductClick

        val layoutManager = StaggeredGridLayoutManager(
            resources.getInteger(R.integer.products_column_count),
            RecyclerView.VERTICAL
        )
        viewBinding.recycler.adapter = adapter
        viewBinding.recycler.layoutManager = layoutManager

        viewModel.getProducts().observe(viewLifecycleOwner, {
            adapter.products = it
            adapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        adapter.onClickListener = null
    }

    private fun onProductClick(product: Product) {
        productDetailsViewModel.fetchProductDetails(product.id)
        MainNavigator.showProductDetails(requireActivity())
    }
}