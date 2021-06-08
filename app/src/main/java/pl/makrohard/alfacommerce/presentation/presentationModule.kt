package pl.makrohard.alfacommerce.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.makrohard.alfacommerce.presentation.categories.CategoriesAdapter
import pl.makrohard.alfacommerce.presentation.categories.CategoriesFragment
import pl.makrohard.alfacommerce.presentation.categories.CategoriesViewModel
import pl.makrohard.alfacommerce.presentation.home.BottomNavigationFragment
import pl.makrohard.alfacommerce.presentation.home.BottomNavigationViewModel
import pl.makrohard.alfacommerce.presentation.product.GalleryAdapter
import pl.makrohard.alfacommerce.presentation.product.ProductDetailsFragment
import pl.makrohard.alfacommerce.presentation.product.ProductDetailsViewModel
import pl.makrohard.alfacommerce.presentation.products.FiltersDialogFragment
import pl.makrohard.alfacommerce.presentation.products.ProductsAdapter
import pl.makrohard.alfacommerce.presentation.products.ProductsFragment
import pl.makrohard.alfacommerce.presentation.products.ProductsViewModel
import pl.makrohard.alfacommerce.presentation.search.SearchFragment
import pl.makrohard.alfacommerce.presentation.search.SearchViewModel

val presentationModule = module {
    fragment {
        BottomNavigationFragment.newInstance()
    }

    fragment {
        CategoriesFragment()
    }

    fragment {
        SearchFragment.newInstance()
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
        BottomNavigationViewModel()
    }

    viewModel {
        CategoriesViewModel(get())
    }

    viewModel {
        SearchViewModel(get())
    }

    viewModel {
        ProductsViewModel(get(), get())
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