package pl.makrohard.alfacommerce.domain.usecase

import pl.makrohard.alfacommerce.data.dto.request.GetProductsRequestDto
import pl.makrohard.alfacommerce.data.dto.response.GetProductsResponseDto
import pl.makrohard.alfacommerce.domain.model.Filters
import pl.makrohard.alfacommerce.domain.repository.ProductsRepository

class GetProductsUseCaseImpl(private val repository: ProductsRepository) :
    GetProductsUseCase {

    override suspend fun invoke(filters: Filters): GetProductsResponseDto {
        return repository.index(GetProductsRequestDto.fromFilters(filters))
    }
}