package pl.makrohard.alfacommerce.api

import pl.makrohard.alfacommerce.model.Product
import retrofit2.Call

interface ProductsApi {
    fun index(): Call<List<Product>>
    fun details(id: Int): Call<Product>
    fun store(product: Product): Call<Void>
    fun edit(product: Product): Call<Void>
    fun delete(id: Int): Call<Void>
}