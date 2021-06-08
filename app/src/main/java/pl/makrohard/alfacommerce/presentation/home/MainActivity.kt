package pl.makrohard.alfacommerce.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.KoinExperimentalAPI
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.databinding.MainActivityBinding
import pl.makrohard.alfacommerce.presentation.products.FiltersDialogFragment

class MainActivity : AppCompatActivity(), TopNavigator {

    private lateinit var viewBinding: MainActivityBinding

    @KoinExperimentalAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        /* Removed because causes crashes on configuration changes
         Because ProductsFragment expects an argument to be passed during creation
         And Koin Fragment Manager apparently can't preserve these passed in CategoriesAdapter's createFragment method */
        //TODO: read more about how Koin fragment factory works and find out how to fix this issue
        //setupKoinFragmentFactory()
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        viewBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    override fun showProductDetails(productId: Int) {
        /*Navigation.findNavController(viewBinding.topContainer)
            .navigate(
                R.id.show_product_details
            )*/
    }

    override fun showFiltersDialog() {
        FiltersDialogFragment.newInstance().show(supportFragmentManager, "showFilters")
    }
}