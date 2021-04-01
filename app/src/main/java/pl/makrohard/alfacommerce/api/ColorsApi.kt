package pl.makrohard.alfacommerce.api

import pl.makrohard.alfacommerce.model.Color
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ColorsApi {
    @GET("colors")
    fun index(): Call<List<Color>>

    @GET("colors/{id}")
    fun details(@Path("id") id: Int): Call<Color>

    @POST("colors")
    fun store(color: Color): Call<Void>
    fun edit(color: Color): Call<Void>
    fun delete(id: Int): Call<Void>
}