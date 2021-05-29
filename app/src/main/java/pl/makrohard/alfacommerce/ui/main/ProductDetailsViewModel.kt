package pl.makrohard.alfacommerce.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.makrohard.alfacommerce.api.ApiClient
import pl.makrohard.alfacommerce.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailsViewModel : ViewModel() {
    private val product = MutableLiveData<Product>()

    fun fetchProductDetails(id: Int) {
        ApiClient.productsApi.details(id).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                product.value = response.body()
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getProduct(): LiveData<Product> {
        return product
    }
}