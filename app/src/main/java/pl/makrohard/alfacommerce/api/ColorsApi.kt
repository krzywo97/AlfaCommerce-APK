package pl.makrohard.alfacommerce.api

import pl.makrohard.alfacommerce.model.Color
import retrofit2.Call
import retrofit2.http.*

interface ColorsApi {
    @GET("colors")
    fun index(): Call<List<Color>>

    @GET("colors/{id}")
    fun details(@Path("id") id: Int): Call<Color>

    @POST("colors")
    fun store(color: Color): Call<Void>

    @PUT("colors")
    fun edit(color: Color): Call<Void>

    @DELETE("colors/{id}")
    fun delete(@Path("id") id: Int): Call<Void>
}