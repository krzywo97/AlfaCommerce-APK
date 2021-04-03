package pl.makrohard.alfacommerce.model.response

import pl.makrohard.alfacommerce.model.Product

data class ProductsList(val products: List<Product>, val totalPages: Int, val totalProducts: Int)