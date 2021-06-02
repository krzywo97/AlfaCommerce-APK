package pl.makrohard.alfacommerce.presentation.categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.makrohard.alfacommerce.domain.model.Category
import pl.makrohard.alfacommerce.domain.model.LoadingState
import pl.makrohard.alfacommerce.domain.usecase.GetCategoriesUseCase

class CategoriesViewModel(private val getCategoriesUseCase: GetCategoriesUseCase) : ViewModel() {
    private val loadingState = MutableLiveData(LoadingState.INITIAL)
    private val categories = MutableLiveData<List<Category>?>()
    private val loadingExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(CategoriesViewModel::class.qualifiedName, "Failed to load data", throwable)

        loadingState.value = LoadingState.FAILED(throwable.localizedMessage ?: "")
        categories.value = null
    }

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        loadingState.value = LoadingState.LOADING
        viewModelScope.launch(Dispatchers.IO + loadingExceptionHandler) {
            getCategoriesUseCase.invoke().let { response ->
                withContext(Dispatchers.Main) {
                    categories.value = response
                    loadingState.value = LoadingState.SUCCESS
                }
            }
        }
    }

    fun getCategories(): LiveData<List<Category>?> {
        return categories
    }
}