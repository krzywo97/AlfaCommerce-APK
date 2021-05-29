package pl.makrohard.alfacommerce.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.makrohard.alfacommerce.api.ApiClient
import pl.makrohard.alfacommerce.model.request.GetProducts
import pl.makrohard.alfacommerce.model.response.ProductsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsViewModel : ViewModel() {
    val filters = GetProducts()
    private val products = MutableLiveData<ProductsList?>()

    init {
        fetchProducts(false)
    }

    fun fetchProducts(append: Boolean) {
        ApiClient.productsApi.index(filters).enqueue(object : Callback<ProductsList> {
            override fun onResponse(call: Call<ProductsList>, response: Response<ProductsList>) {
                if (append && products.value != null && response.body() != null) {
                    val list =
                        products.value!!.products + response.body()!!.products
                    products.value = ProductsList(
                        list,
                        response.body()!!.totalPages,
                        response.body()!!.totalProducts
                    )
                } else {
                    products.value = response.body()
                }
            }

            override fun onFailure(call: Call<ProductsList>, t: Throwable) {
                if (!append) {
                    products.value = null
                }
            }
        })
    }

    fun getProducts(): LiveData<ProductsList?> {
        return products
    }
}