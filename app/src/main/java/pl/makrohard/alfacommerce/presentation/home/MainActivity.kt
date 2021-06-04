package pl.makrohard.alfacommerce.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.core.KoinExperimentalAPI
import pl.makrohard.alfacommerce.R

class MainActivity : AppCompatActivity() {

    @KoinExperimentalAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}