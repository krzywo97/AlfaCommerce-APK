package pl.makrohard.alfacommerce.util

import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.showKeyboard(imm: InputMethodManager) {
    this.requestFocus()
    imm.toggleSoftInput(
        InputMethodManager.SHOW_IMPLICIT,
        InputMethodManager.HIDE_IMPLICIT_ONLY
    )
}

fun View.hideKeyboard(imm: InputMethodManager) {
    imm.hideSoftInputFromWindow(
        windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}