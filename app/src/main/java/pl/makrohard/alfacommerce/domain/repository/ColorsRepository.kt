package pl.makrohard.alfacommerce.domain.repository

import pl.makrohard.alfacommerce.domain.model.Color
import retrofit2.http.GET
import retrofit2.http.Path

interface ColorsRepository {
    @GET("colors")
    suspend fun index(): Result<List<Color>>

    @GET("colors/{id}")
    suspend fun details(@Path("id") id: Int): Result<Color>
}