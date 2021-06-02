package pl.makrohard.alfacommerce.domain.usecase

import pl.makrohard.alfacommerce.data.dto.request.GetProductsRequestDto
import pl.makrohard.alfacommerce.data.dto.response.GetProductsResponseDto

interface GetProductsUseCase {
    suspend fun invoke(productsRequestDto: GetProductsRequestDto): Result<GetProductsResponseDto>
}