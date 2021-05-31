package pl.makrohard.alfacommerce.presentation.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.makrohard.alfacommerce.domain.model.LoadingState
import pl.makrohard.alfacommerce.domain.model.Product
import pl.makrohard.alfacommerce.domain.repository.ProductsRepository

class ProductDetailsViewModel(val repository: ProductsRepository) : ViewModel() {
    private val loadingState = MutableLiveData(LoadingState.INITIAL)
    private val product = MutableLiveData<Product>()
    private val loadingExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        loadingState.value = LoadingState.FAILED(throwable.localizedMessage ?: "")
    }

    fun fetchProductDetails(id: Int) {
        loadingState.value = LoadingState.LOADING
        viewModelScope.launch(Dispatchers.IO + loadingExceptionHandler) {
            product.value = repository.details(id)
            loadingState.value = LoadingState.SUCCESS
        }
    }

    fun getProduct(): LiveData<Product> {
        return product
    }
}