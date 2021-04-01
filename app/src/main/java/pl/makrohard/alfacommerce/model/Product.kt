package pl.makrohard.alfacommerce.model

data class Product(
    val id: Int,
    val name: String,
    val price: Float,
    val weight: Int,
    val color: Color,
    val categories: List<Category>,
    val photos: List<Photo>
)