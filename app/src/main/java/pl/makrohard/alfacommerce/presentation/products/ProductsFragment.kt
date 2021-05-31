package pl.makrohard.alfacommerce.presentation.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.koin.android.ext.android.inject
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.databinding.ProductsFragmentBinding
import pl.makrohard.alfacommerce.domain.model.Product
import pl.makrohard.alfacommerce.presentation.home.MainNavigator

class ProductsFragment : Fragment() {
    companion object {
        private const val CATEGORY_ID = "category_id"

        fun newInstance(id: Int): ProductsFragment {
            val fragment = ProductsFragment()
            val bundle = Bundle()
            bundle.putInt(CATEGORY_ID, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var viewBinding: ProductsFragmentBinding
    private lateinit var viewModel: ProductsViewModel
    private val adapter: ProductsAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val category = arguments?.getInt(CATEGORY_ID) ?: -1
        viewModel = ViewModelProvider(this).get(category.toString(), ProductsViewModel::class.java)
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
            adapter.products = it?.products ?: emptyList()
            adapter.notifyDataSetChanged()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        adapter.onClickListener = null
    }

    private fun onProductClick(product: Product) {
        MainNavigator.showProductDetails(product.id, requireActivity())
    }
}