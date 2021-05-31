package pl.makrohard.alfacommerce.presentation.categories

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import pl.makrohard.alfacommerce.domain.model.Category
import pl.makrohard.alfacommerce.presentation.products.ProductsFragment

class CategoriesAdapter(
    var categories: List<Category>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return categories.size
    }

    override fun createFragment(position: Int): Fragment {
        return ProductsFragment.newInstance(categories[position].id)
    }
}