package pl.makrohard.alfacommerce.presentation.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.makrohard.alfacommerce.databinding.SearchFragmentBinding
import pl.makrohard.alfacommerce.domain.model.Filters
import pl.makrohard.alfacommerce.presentation.products.ProductsAdapter

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewBinding: SearchFragmentBinding
    private val searchViewModel: SearchViewModel by viewModel()
    private val adapter: ProductsAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = SearchFragmentBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.searchResultsRecycler.adapter = adapter
        viewBinding.searchResultsRecycler.layoutManager = GridLayoutManager(requireContext(), 2)

        viewBinding.searchBox.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                searchViewModel.search(Filters((v as EditText).text.toString()))
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        searchViewModel.getLoadingState().observe(viewLifecycleOwner) {
            //TODO: update UI
        }

        searchViewModel.getFilters().observe(viewLifecycleOwner) {
            //TODO: update UI
        }

        searchViewModel.getResults().observe(viewLifecycleOwner) {
            adapter.products = it
            adapter.notifyDataSetChanged()
        }
    }
}