package pl.makrohard.alfacommerce.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.makrohard.alfacommerce.api.ApiClient
import pl.makrohard.alfacommerce.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesViewModel : ViewModel() {
    private val categories = MutableLiveData<List<Category>>()

    fun fetchCategories() {
        ApiClient.categoriesApi.index().enqueue(object : Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                categories.value = response.body()
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {

            }
        })
    }

    fun getCategories(): LiveData<List<Category>> {
        return categories
    }
}