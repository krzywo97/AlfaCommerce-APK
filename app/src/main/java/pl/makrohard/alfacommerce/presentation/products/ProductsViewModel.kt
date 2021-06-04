package pl.makrohard.alfacommerce.presentation.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.makrohard.alfacommerce.domain.model.Filters
import pl.makrohard.alfacommerce.domain.model.LoadingState
import pl.makrohard.alfacommerce.domain.model.Product
import pl.makrohard.alfacommerce.domain.usecase.GetProductsUseCase

class ProductsViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    val filters: Filters
) : ViewModel() {

    private val loadingState = MutableLiveData(LoadingState.INITIAL)
    private val products = MutableLiveData<List<Product>>(listOf())
    private val totalPages = MutableLiveData(0)
    private val totalProducts = MutableLiveData(0)

    private val loadingExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        loadingState.value = LoadingState.FAILED(throwable.localizedMessage ?: "")
    }

    fun fetchProducts(append: Boolean) {
        loadingState.value = LoadingState.LOADING

        viewModelScope.launch(Dispatchers.IO + loadingExceptionHandler) {
            getProductsUseCase.invoke(filters).let { response ->
                withContext(Dispatchers.Main) {
                    products.value = if (!append) {
                        response.products
                    } else {
                        products.value!! + response.products
                    }

                    totalPages.value = response.totalPages
                    totalProducts.value = response.totalProducts

                    loadingState.value = LoadingState.SUCCESS
                }
            }
        }
    }

    fun getProducts(): LiveData<List<Product>> {
        return products
    }

    fun getTotalPages(): LiveData<Int> {
        return totalPages
    }

    fun getTotalProducts(): LiveData<Int> {
        return totalProducts
    }
}