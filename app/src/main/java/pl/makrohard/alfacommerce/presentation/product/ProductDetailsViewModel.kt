package pl.makrohard.alfacommerce.presentation.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.makrohard.alfacommerce.domain.model.LoadingState
import pl.makrohard.alfacommerce.domain.model.Product
import pl.makrohard.alfacommerce.domain.usecase.GetProductDetailsUseCase

class ProductDetailsViewModel(private val getProductDetailsUseCase: GetProductDetailsUseCase) :
    ViewModel() {

    private val loadingState = MutableLiveData(LoadingState.INITIAL)
    private val product = MutableLiveData<Product>()
    private val loadingExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        loadingState.value = LoadingState.FAILED(throwable.localizedMessage ?: "")
    }

    fun fetchProductDetails(productId: Int) {
        loadingState.value = LoadingState.LOADING
        viewModelScope.launch(Dispatchers.IO + loadingExceptionHandler) {
            getProductDetailsUseCase.invoke(productId).let { result ->
                withContext(Dispatchers.Main) {
                    product.value = result
                    loadingState.value = LoadingState.SUCCESS
                }
            }
        }
    }

    fun getProduct(): LiveData<Product> {
        return product
    }
}