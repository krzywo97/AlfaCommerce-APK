package pl.makrohard.alfacommerce.presentation.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import pl.makrohard.alfacommerce.databinding.CategoriesFragmentBinding

class CategoriesFragment : Fragment() {
    private lateinit var viewBinding: CategoriesFragmentBinding
    private val categoriesViewModel: CategoriesViewModel by viewModel()
    private lateinit var adapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        viewBinding = CategoriesFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = get {
            parametersOf(childFragmentManager, lifecycle)
        }
        viewBinding.pager.offscreenPageLimit = 2
        viewBinding.pager.adapter = adapter

        TabLayoutMediator(viewBinding.tabs, viewBinding.pager) { tab, position ->
            tab.text = categoriesViewModel.getCategories().value!![position].name
        }.attach()

        categoriesViewModel.getCategories().observe(viewLifecycleOwner) {
            adapter.categories = it
            adapter.notifyDataSetChanged()
        }
    }
}