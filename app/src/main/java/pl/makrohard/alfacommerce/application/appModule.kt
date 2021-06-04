package pl.makrohard.alfacommerce.application

import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module
import pl.makrohard.alfacommerce.presentation.categories.CategoriesFragment
import pl.makrohard.alfacommerce.presentation.product.ProductDetailsFragment
import pl.makrohard.alfacommerce.presentation.products.FiltersDialogFragment
import pl.makrohard.alfacommerce.presentation.products.ProductsFragment

val appModule = module {
    fragment {
        CategoriesFragment()
    }

    fragment { (categoryId: Int) ->
        ProductsFragment.newInstance(categoryId)
    }

    fragment { (productId: Int) ->
        ProductDetailsFragment.newInstance(productId)
    }

    fragment {
        FiltersDialogFragment.newInstance()
    }
}