package pl.makrohard.alfacommerce.model

data class Filters(
    val name: String? = null,
    val minPrice: Float? = null,
    val maxPrice: Float? = null,
    val minWeight: Float? = null,
    val maxWeight: Float? = null,
    val colorId: Int? = null,
    val category: Int? = null
)