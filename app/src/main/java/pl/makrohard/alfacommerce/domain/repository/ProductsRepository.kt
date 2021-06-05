package pl.makrohard.alfacommerce.domain.repository

import pl.makrohard.alfacommerce.data.dto.response.GetProductsResponseDto
import pl.makrohard.alfacommerce.domain.model.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ProductsRepository {
    @GET("products")
    @JvmSuppressWildcards
    suspend fun index(@QueryMap filters: Map<String, Any>): GetProductsResponseDto

    @GET("products/{id}")
    suspend fun details(@Path("id") id: Int): Product
}