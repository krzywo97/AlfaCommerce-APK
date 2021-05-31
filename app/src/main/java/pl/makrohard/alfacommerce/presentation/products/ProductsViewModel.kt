package pl.makrohard.alfacommerce.presentation.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.makrohard.alfacommerce.data.dto.request.GetProductsRequestDto
import pl.makrohard.alfacommerce.domain.model.LoadingState
import pl.makrohard.alfacommerce.domain.model.Product
import pl.makrohard.alfacommerce.domain.repository.ProductsRepository

class ProductsViewModel(val repository: ProductsRepository) : ViewModel() {
    val filters = GetProductsRequestDto()

    private val loadingState = MutableLiveData(LoadingState.INITIAL)
    private val products = MutableLiveData<List<Product>>(listOf())
    private val totalPages = MutableLiveData(0)
    private val totalProducts = MutableLiveData(0)

    private val loadingExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        loadingState.value = LoadingState.FAILED(throwable.localizedMessage ?: "")
    }

    init {
        fetchProducts(false)
    }

    fun fetchProducts(append: Boolean) {
        loadingState.value = LoadingState.LOADING

        viewModelScope.launch(Dispatchers.IO + loadingExceptionHandler) {
            val response = repository.index(filters)
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