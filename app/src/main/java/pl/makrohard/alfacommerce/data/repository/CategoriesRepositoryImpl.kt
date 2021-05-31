package pl.makrohard.alfacommerce.data.repository

import pl.makrohard.alfacommerce.application.Constants
import pl.makrohard.alfacommerce.domain.model.Category
import pl.makrohard.alfacommerce.domain.repository.CategoriesRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoriesRepositoryImpl : CategoriesRepository {
    private var repository = Retrofit.Builder()
        .baseUrl(Constants.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CategoriesRepository::class.java)

    override suspend fun index(): List<Category> {
        return repository.index()
    }

    override suspend fun details(id: Int): Category {
        return repository.details(id)
    }
}