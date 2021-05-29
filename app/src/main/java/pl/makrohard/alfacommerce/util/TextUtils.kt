package pl.makrohard.alfacommerce.util

import java.text.NumberFormat
import java.util.*

object TextUtils {
    fun formatPrice(value: Float): String {
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        format.currency = Currency.getInstance("PLN")
        return format.format(value)
    }
}