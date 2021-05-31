package pl.makrohard.alfacommerce.presentation.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.makrohard.alfacommerce.data.repository.ProductsRepositoryImpl
import pl.makrohard.alfacommerce.domain.model.Category
import pl.makrohard.alfacommerce.domain.model.LoadingState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesViewModel : ViewModel() {

    private val loadingState = MutableLiveData(LoadingState.INITIAL)
    private val categories = MutableLiveData<List<Category>>()

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            loadingState.value = LoadingState.LOADING
            ProductsRepositoryImpl.categoriesApi.index().enqueue(object : Callback<List<Category>> {
                override fun onResponse(
                    call: Call<List<Category>>,
                    response: Response<List<Category>>
                ) {
                    categories.value = response.body()
                    loadingState.value = LoadingState.SUCCESS
                }

                override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                    loadingState.value = LoadingState.FAILED(t.localizedMessage ?: "")
                }
            })
        }
    }

    fun getCategories(): LiveData<List<Category>> {
        return categories
    }
}