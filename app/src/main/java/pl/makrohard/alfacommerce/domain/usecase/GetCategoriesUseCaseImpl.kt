package pl.makrohard.alfacommerce.domain.usecase

import pl.makrohard.alfacommerce.domain.model.Category
import pl.makrohard.alfacommerce.domain.repository.CategoriesRepository

class GetCategoriesUseCaseImpl(private val repository: CategoriesRepository) :
    GetCategoriesUseCase {

    override suspend fun invoke(): List<Category> {
        return repository.index()
    }
}