package pl.makrohard.alfacommerce.api

import pl.makrohard.alfacommerce.model.Product
import pl.makrohard.alfacommerce.model.request.GetProducts
import pl.makrohard.alfacommerce.model.response.ProductsList
import retrofit2.Call
import retrofit2.http.*

interface ProductsApi {
    @GET("products")
    fun index(@QueryMap filters: GetProducts): Call<ProductsList>

    @GET("products/{id}")
    fun details(@Path("id") id: Int): Call<Product>

    @POST("products")
    fun store(product: Product): Call<Void>

    @PUT("products")
    fun edit(product: Product): Call<Void>

    @DELETE("products/{id}")
    fun delete(@Path("id") id: Int): Call<Void>
}