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
import pl.makrohard.alfacommerce.domain.usecase.GetProductsUseCase

class ProductsViewModel(private val getProductsUseCase: GetProductsUseCase) : ViewModel() {
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
            getProductsUseCase.invoke(filters).let { response ->
                val data = response.getOrNull()!!
                products.value = if (!append) {
                    data.products
                } else {
                    products.value!! + data.products
                }

                totalPages.value = data.totalPages
                totalProducts.value = data.totalProducts

                loadingState.value = LoadingState.SUCCESS
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