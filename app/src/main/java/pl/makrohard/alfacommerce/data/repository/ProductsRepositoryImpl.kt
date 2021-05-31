package pl.makrohard.alfacommerce.data.repository

import pl.makrohard.alfacommerce.Constants
import pl.makrohard.alfacommerce.data.dto.request.GetProductsRequestDto
import pl.makrohard.alfacommerce.data.dto.response.GetProductsResponseDto
import pl.makrohard.alfacommerce.domain.model.Product
import pl.makrohard.alfacommerce.domain.repository.ProductsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsRepositoryImpl : ProductsRepository {
    private val repository = Retrofit.Builder()
        .baseUrl(Constants.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ProductsRepository::class.java)

    override suspend fun index(filters: GetProductsRequestDto): GetProductsResponseDto {
        return repository.index(filters)
    }

    override suspend fun details(id: Int): Product {
        return repository.details(id)
    }
}