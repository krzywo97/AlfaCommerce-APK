package pl.makrohard.alfacommerce.api

import pl.makrohard.alfacommerce.model.Category
import retrofit2.Call
import retrofit2.http.*

interface CategoriesApi {
    @GET("categories")
    fun index(): Call<List<Category>>

    @GET("categories/{id}")
    fun details(@Path("id") id: Int): Call<Category>

    @POST("categories")
    fun store(category: Category): Call<Void>

    @PUT("categories")
    fun edit(category: Category): Call<Void>

    @DELETE("categories/{id}")
    fun delete(@Path("id") id: Int): Call<Void>
}