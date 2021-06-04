package pl.makrohard.alfacommerce.domain.usecase

import pl.makrohard.alfacommerce.domain.model.Product

interface GetProductDetailsUseCase {
    suspend fun invoke(id: Int): Product
}