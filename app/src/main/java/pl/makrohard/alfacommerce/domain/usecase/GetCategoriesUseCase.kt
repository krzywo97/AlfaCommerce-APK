package pl.makrohard.alfacommerce.domain.usecase

import pl.makrohard.alfacommerce.domain.model.Category

interface GetCategoriesUseCase {
    suspend fun invoke(): List<Category>
}