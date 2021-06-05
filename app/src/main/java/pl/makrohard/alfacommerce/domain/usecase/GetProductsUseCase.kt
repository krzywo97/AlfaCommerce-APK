package pl.makrohard.alfacommerce.domain.usecase

import pl.makrohard.alfacommerce.data.dto.response.GetProductsResponseDto
import pl.makrohard.alfacommerce.domain.model.Filters

interface GetProductsUseCase {
    suspend fun invoke(filters: Filters): GetProductsResponseDto
}