package pl.makrohard.alfacommerce.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get
import pl.makrohard.alfacommerce.domain.model.Filters
import pl.makrohard.alfacommerce.domain.model.LoadingState
import pl.makrohard.alfacommerce.domain.model.Product
import pl.makrohard.alfacommerce.domain.usecase.GetProductsUseCase

class SearchViewModel(private val getProductsUseCase: GetProductsUseCase) : ViewModel() {

    private val loadingState = MutableLiveData(LoadingState.INITIAL)
    private val filters = MutableLiveData<Filters>(get(Filters::class.java))
    private val results = MutableLiveData<List<Product>>()

    private val loadingExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        loadingState.value = LoadingState.FAILED(throwable.localizedMessage ?: "")
    }

    fun search(filters: Filters) {
        this.filters.value = filters
        this.loadingState.value = LoadingState.LOADING

        viewModelScope.launch(Dispatchers.IO + loadingExceptionHandler) {
            getProductsUseCase.invoke(filters).let { response ->
                withContext(Dispatchers.Main) {
                    results.value = response.products
                    loadingState.value = LoadingState.SUCCESS
                }
            }
        }
    }

    fun getLoadingState(): LiveData<LoadingState> {
        return loadingState
    }

    fun getFilters(): LiveData<Filters> {
        return filters
    }

    fun getResults(): LiveData<List<Product>> {
        return results
    }
}