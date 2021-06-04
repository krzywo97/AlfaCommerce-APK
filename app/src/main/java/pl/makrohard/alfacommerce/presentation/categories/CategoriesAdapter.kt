package pl.makrohard.alfacommerce.presentation.categories

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get
import pl.makrohard.alfacommerce.domain.model.Category
import pl.makrohard.alfacommerce.presentation.products.ProductsFragment

class CategoriesAdapter(
    var categories: List<Category>?,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return categories?.size ?: 0
    }

    override fun createFragment(position: Int): Fragment {
        return get(ProductsFragment::class.java) {
            parametersOf(categories!![position].id)
        }
    }
}