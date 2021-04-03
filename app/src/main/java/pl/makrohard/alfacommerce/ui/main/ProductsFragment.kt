package pl.makrohard.alfacommerce.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.adapter.ProductsAdapter
import pl.makrohard.alfacommerce.databinding.ProductsFragmentBinding

class ProductsFragment : Fragment() {
    private lateinit var viewBinding: ProductsFragmentBinding

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private val viewModel: ProductsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.fetchProducts()
        setHasOptionsMenu(true)
        viewBinding = ProductsFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filters -> {
                Toast.makeText(activity, "Filtry", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ProductsAdapter(viewModel.getProducts().value?.products ?: emptyList())
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
}