package pl.makrohard.alfacommerce.presentation.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.makrohard.alfacommerce.domain.repository.ProductsRepository
import pl.makrohard.alfacommerce.domain.model.LoadingState
import pl.makrohard.alfacommerce.data.dto.request.GetProductsRequestDto
import pl.makrohard.alfacommerce.data.dto.response.GetProductsResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsViewModel : ViewModel() {
    val filters = GetProductsRequestDto()
    private val loadingState = MutableLiveData(LoadingState.INITIAL)
    private val products = MutableLiveData<GetProductsResponseDto?>()

    lateinit var productsRepository: ProductsRepository

    init {
        fetchProducts(false)
    }

    fun fetchProducts(append: Boolean) {
        viewModelScope.launch {
            loadingState.value = LoadingState.LOADING
            productsRepository.index(filters).enqueue(object : Callback<GetProductsResponseDto> {
                override fun onResponse(
                    call: Call<GetProductsResponseDto>,
                    response: Response<GetProductsResponseDto>
                ) {
                    loadingState.value = LoadingState.SUCCESS
                    if (append && products.value != null && response.body() != null) {
                        val list =
                            products.value!!.products + response.body()!!.products

                        products.value = GetProductsResponseDto(
                            list,
                            response.body()!!.totalPages,
                            response.body()!!.totalProducts
                        )
                    } else {
                        products.value = response.body()
                    }
                }

                override fun onFailure(call: Call<GetProductsResponseDto>, t: Throwable) {
                    loadingState.value = LoadingState.FAILED(t.localizedMessage ?: "")
                    if (!append) {
                        products.value = null
                    }
                }
            })
        }
    }

    fun getProducts(): LiveData<GetProductsResponseDto?> {
        return products
    }
}