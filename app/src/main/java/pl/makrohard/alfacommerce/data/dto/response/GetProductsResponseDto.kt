package pl.makrohard.alfacommerce.data.dto.response

import pl.makrohard.alfacommerce.domain.model.Product

data class GetProductsResponseDto(
    val products: List<Product>,
    val totalPages: Int,
    val totalProducts: Int
)