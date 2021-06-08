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
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.databinding.ProductsFragmentBinding
import pl.makrohard.alfacommerce.domain.model.Category
import pl.makrohard.alfacommerce.domain.model.Product
import pl.makrohard.alfacommerce.presentation.home.TopNavigator
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
    private val productsViewModel: ProductsViewModel by viewModel()
    private val productDetailsViewModel: ProductDetailsViewModel by sharedViewModel()
    private val adapter: ProductsAdapter by inject()

    private var topNavigator: TopNavigator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val category = arguments?.getInt(CATEGORY_ID) ?: -1

        //TODO: Don't create new Category object, get it from the CategoriesViewModel instead
        productsViewModel.filters.category = listOf(Category(category, ""))
        productsViewModel.fetchProducts(false)

        viewBinding = ProductsFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()
        if (activity is TopNavigator) {
            topNavigator = activity
        }
        adapter.onClickListener = this::onProductClick

        val layoutManager = StaggeredGridLayoutManager(
            resources.getInteger(R.integer.products_column_count),
            RecyclerView.VERTICAL
        )
        viewBinding.recycler.adapter = adapter
        viewBinding.recycler.layoutManager = layoutManager

        productsViewModel.getProducts().observe(viewLifecycleOwner, {
            adapter.products = it
            adapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        topNavigator = null
        adapter.onClickListener = null
    }

    private fun onProductClick(product: Product) {
        productDetailsViewModel.fetchProductDetails(product.id)
        topNavigator?.showProductDetails(product.id)
    }
}