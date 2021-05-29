package pl.makrohard.alfacommerce.ui

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.ui.main.ProductDetailsFragment

object MainNavigator {
    fun showProductDetails(productId: Int, activity: Activity) {
        Navigation.findNavController(activity, R.id.container)
            .navigate(
                R.id.show_product_details, bundleOf(
                    ProductDetailsFragment.PRODUCT_ID to productId
                )
            )
    }
}