package pl.makrohard.alfacommerce.presentation.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.makrohard.alfacommerce.databinding.CategoriesFragmentBinding

class CategoriesFragment : Fragment() {
    private lateinit var viewBinding: CategoriesFragmentBinding
    private val viewModel by viewModel<CategoriesViewModel>()

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

        val adapter = CategoriesAdapter(
            viewModel.getCategories().value,
            childFragmentManager,
            lifecycle
        )

        viewBinding.pager.apply {
            offscreenPageLimit = 2
            this.adapter = adapter
        }

        TabLayoutMediator(viewBinding.tabs, viewBinding.pager) { tab, position ->
            tab.text = viewModel.getCategories().value!![position].name
        }.attach()

        viewModel.getCategories().observe(viewLifecycleOwner) {
            adapter.categories = it
            adapter.notifyDataSetChanged()
        }
    }
}