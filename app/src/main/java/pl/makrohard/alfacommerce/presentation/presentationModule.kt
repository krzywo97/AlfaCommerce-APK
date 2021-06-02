package pl.makrohard.alfacommerce.presentation

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.makrohard.alfacommerce.presentation.categories.CategoriesViewModel

val presentationModule = module {
    viewModel {
        CategoriesViewModel(get())
    }
}