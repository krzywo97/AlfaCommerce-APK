package pl.makrohard.alfacommerce.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.adapter.CategoriesAdapter
import pl.makrohard.alfacommerce.databinding.CategoriesFragmentBinding

class CategoriesFragment : Fragment() {
    companion object {
        fun newInstance() = CategoriesFragment()
    }

    private lateinit var viewBinding: CategoriesFragmentBinding
    private val viewModel: CategoriesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.fetchCategories()
        viewBinding = CategoriesFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoriesAdapter(
            viewModel.getCategories().value ?: emptyList(),
            childFragmentManager,
            lifecycle
        )
        viewBinding.pager.adapter = adapter
        TabLayoutMediator(viewBinding.tabs, viewBinding.pager) { tab, position ->
            tab.text = viewModel.getCategories().value?.get(position)?.name ?: "?"
        }.attach()

        viewModel.getCategories().observe(requireActivity(), {
            adapter.categories = it
            adapter.notifyDataSetChanged()
        })
    }

}