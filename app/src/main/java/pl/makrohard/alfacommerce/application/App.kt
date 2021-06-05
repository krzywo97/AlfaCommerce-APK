package pl.makrohard.alfacommerce.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.KoinExperimentalAPI
import org.koin.core.context.startKoin
import pl.makrohard.alfacommerce.data.repository.repositoryModule
import pl.makrohard.alfacommerce.domain.domainModule
import pl.makrohard.alfacommerce.presentation.presentationModule

class App : Application() {
    @KoinExperimentalAPI
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger()
            fragmentFactory()
            modules(
                appModule,
                repositoryModule,
                presentationModule,
                domainModule
            )
        }
    }
}