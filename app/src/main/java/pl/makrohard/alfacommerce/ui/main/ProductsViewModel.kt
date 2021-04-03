package pl.makrohard.alfacommerce.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.makrohard.alfacommerce.api.ApiClient
import pl.makrohard.alfacommerce.model.Product
import pl.makrohard.alfacommerce.model.request.GetProducts
import pl.makrohard.alfacommerce.model.response.ProductsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsViewModel : ViewModel() {
    val filters = GetProducts()
    private val response = MutableLiveData<ProductsList?>()

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        ApiClient.productsApi.index(filters).enqueue(object : Callback<ProductsList> {
            override fun onResponse(call: Call<ProductsList>, response: Response<ProductsList>) {
                this@ProductsViewModel.response.value = response.body()
            }

            override fun onFailure(call: Call<ProductsList>, t: Throwable) {
                this@ProductsViewModel.response.value = null
            }
        })
    }

    fun getProducts(): LiveData<ProductsList?> {
        return response
    }
}