package pl.makrohard.alfacommerce.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.makrohard.alfacommerce.domain.repository.CategoriesRepository

class CategoriesViewModelFactory(val repository: CategoriesRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoriesViewModel(repository) as T
    }
}