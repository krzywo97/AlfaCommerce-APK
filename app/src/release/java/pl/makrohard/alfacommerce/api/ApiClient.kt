package pl.makrohard.alfacommerce.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://makrohard.pl/alfacommerce/api/v1"

    private val client: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val categoriesApi: CategoriesApi = client.create(CategoriesApi::class.java)
    val colorsApi: ColorsApi = client.create(ColorsApi::class.java)
    val productsApi: ProductsApi = client.create(ProductsApi::class.java)
}