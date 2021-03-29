package pl.makrohard.alfacommerce.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.ui.main.ProductsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProductsFragment.newInstance())
                .commitNow()
        }
    }
}