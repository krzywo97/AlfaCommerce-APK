package pl.makrohard.alfacommerce.domain.repository

import pl.makrohard.alfacommerce.domain.model.Category
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoriesRepository {
    @GET("categories")
    suspend fun index(): List<Category>

    @GET("categories/{id}")
    suspend fun details(@Path("id") id: Int): Category
}