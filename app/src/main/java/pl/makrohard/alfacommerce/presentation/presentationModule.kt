package pl.makrohard.alfacommerce.presentation

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.makrohard.alfacommerce.domain.model.Filters
import pl.makrohard.alfacommerce.presentation.categories.CategoriesViewModel
import pl.makrohard.alfacommerce.presentation.product.ProductDetailsViewModel
import pl.makrohard.alfacommerce.presentation.products.ProductsAdapter
import pl.makrohard.alfacommerce.presentation.products.ProductsViewModel

val presentationModule = module {
    viewModel {
        CategoriesViewModel(get())
    }

    viewModel { (filters: Filters) ->
        ProductsViewModel(get(), filters)
    }

    viewModel {
        ProductDetailsViewModel(get())
    }

    factory {
        ProductsAdapter()
    }
}