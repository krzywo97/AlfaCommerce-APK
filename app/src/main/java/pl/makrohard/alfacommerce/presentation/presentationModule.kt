package pl.makrohard.alfacommerce.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.makrohard.alfacommerce.domain.model.Filters
import pl.makrohard.alfacommerce.presentation.categories.CategoriesAdapter
import pl.makrohard.alfacommerce.presentation.categories.CategoriesFragment
import pl.makrohard.alfacommerce.presentation.categories.CategoriesViewModel
import pl.makrohard.alfacommerce.presentation.product.GalleryAdapter
import pl.makrohard.alfacommerce.presentation.product.ProductDetailsFragment
import pl.makrohard.alfacommerce.presentation.product.ProductDetailsViewModel
import pl.makrohard.alfacommerce.presentation.products.FiltersDialogFragment
import pl.makrohard.alfacommerce.presentation.products.ProductsAdapter
import pl.makrohard.alfacommerce.presentation.products.ProductsFragment
import pl.makrohard.alfacommerce.presentation.products.ProductsViewModel

val presentationModule = module {
    fragment {
        CategoriesFragment()
    }

    fragment { (categoryId: Int) ->
        ProductsFragment.newInstance(categoryId)
    }

    fragment {
        ProductDetailsFragment.newInstance()
    }

    fragment {
        FiltersDialogFragment.newInstance()
    }

    viewModel {
        CategoriesViewModel(get())
    }

    viewModel { (filters: Filters) ->
        ProductsViewModel(get(), filters)
    }

    viewModel {
        ProductDetailsViewModel(get())
    }

    factory { (fragmentManager: FragmentManager, lifecycle: Lifecycle) ->
        CategoriesAdapter(fragmentManager, lifecycle)
    }

    factory {
        ProductsAdapter()
    }

    factory {
        GalleryAdapter()
    }
}