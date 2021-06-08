package pl.makrohard.alfacommerce.domain.model

data class Filters(
    var name: String? = null,
    var category: List<Category>? = null,
    var color: List<Color>? = null,
    var minPrice: Float? = null,
    var maxPrice: Float? = null,
    var minWeight: Int? = null,
    var maxWeight: Int? = null,
    var page: Int? = null,
    var perPage: Int? = null
)