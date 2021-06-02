package pl.makrohard.alfacommerce.domain.usecase

import pl.makrohard.alfacommerce.domain.model.Product
import pl.makrohard.alfacommerce.domain.repository.ProductsRepository

class GetProductDetailsUseCaseImpl(private val repository: ProductsRepository) :
    GetProductDetailsUseCase {

    override suspend fun invoke(id: Int): Result<Product> {
        return repository.details(id)
    }
}