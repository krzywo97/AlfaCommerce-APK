package pl.makrohard.alfacommerce.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pl.makrohard.alfacommerce.data.repository.repositoryModule
import pl.makrohard.alfacommerce.presentation.presentationModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(repositoryModule, presentationModule)
        }
    }
}