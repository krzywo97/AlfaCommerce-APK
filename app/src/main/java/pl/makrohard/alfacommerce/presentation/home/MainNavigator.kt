package pl.makrohard.alfacommerce.presentation.home

import android.app.Activity
import androidx.navigation.Navigation
import pl.makrohard.alfacommerce.R

object MainNavigator {
    fun showProductDetails(activity: Activity) {
        Navigation.findNavController(activity, R.id.container)
            .navigate(
                R.id.show_product_details
            )
    }
}