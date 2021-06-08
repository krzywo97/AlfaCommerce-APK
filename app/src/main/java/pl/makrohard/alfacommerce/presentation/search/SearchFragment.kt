package pl.makrohard.alfacommerce.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
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

        viewBinding.searchBox.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchViewModel.search(Filters(v.text.toString()))
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
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