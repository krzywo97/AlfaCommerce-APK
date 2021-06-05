package pl.makrohard.alfacommerce.data.repository

import pl.makrohard.alfacommerce.application.Constants
import pl.makrohard.alfacommerce.domain.model.Color
import pl.makrohard.alfacommerce.domain.repository.ColorsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiColorsRepository : ColorsRepository {
    private var repository = Retrofit.Builder()
        .baseUrl(Constants.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ColorsRepository::class.java)

    override suspend fun index(): Result<List<Color>> {
        return repository.index()
    }

    override suspend fun details(id: Int): Result<Color> {
        return repository.details(id)
    }
}