package pl.makrohard.alfacommerce.ui.main

import androidx.lifecycle.ViewModel
import pl.makrohard.alfacommerce.model.Filters
import pl.makrohard.alfacommerce.model.Product

class ProductsViewModel : ViewModel() {
    private val filters = Filters()
    private val products = ArrayList<Product>()
}