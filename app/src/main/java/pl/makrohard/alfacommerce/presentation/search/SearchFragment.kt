package pl.makrohard.alfacommerce.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.makrohard.alfacommerce.databinding.SearchFragmentBinding
import pl.makrohard.alfacommerce.domain.model.Filters
import pl.makrohard.alfacommerce.presentation.home.TopNavigator
import pl.makrohard.alfacommerce.presentation.products.ProductsAdapter

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewBinding: SearchFragmentBinding
    private val searchViewModel: SearchViewModel by viewModel()
    private val adapter: ProductsAdapter by inject()
    private val filters: Filters by inject()
    private var topNavigator: TopNavigator? = null
    private lateinit var imm: InputMethodManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = SearchFragmentBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()
        if (activity is TopNavigator) {
            topNavigator = activity
        }
        imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        viewBinding.searchResultsRecycler.adapter = adapter
        viewBinding.searchResultsRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        viewBinding.searchBox.postDelayed({
            if (viewBinding.searchBox.isAttachedToWindow) {
                viewBinding.searchBox.requestFocus()
                imm.toggleSoftInput(
                    InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
                )
            }
        }, 150)

        viewBinding.searchBox.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                imm.hideSoftInputFromWindow(
                    viewBinding.root.windowToken,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
                )
            }
        }

        viewBinding.searchBox.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                filters.name = v.text.toString()
                searchViewModel.search(filters)
                imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        viewBinding.sortButton.setOnLongClickListener {
            //TODO: handle click
            return@setOnLongClickListener true
        }

        viewBinding.filtersButton.setOnClickListener {
            topNavigator?.showFiltersDialog()
        }

        searchViewModel.getLoadingState().observe(viewLifecycleOwner) {
            //TODO: update UI
        }

        searchViewModel.getResults().observe(viewLifecycleOwner) {
            adapter.products = it
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        topNavigator = null
    }
}