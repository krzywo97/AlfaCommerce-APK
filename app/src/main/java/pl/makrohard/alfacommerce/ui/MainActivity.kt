package pl.makrohard.alfacommerce.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.ui.main.CategoriesFragment
import pl.makrohard.alfacommerce.ui.main.ProductsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CategoriesFragment.newInstance())
                .commitNow()
        }
    }
}