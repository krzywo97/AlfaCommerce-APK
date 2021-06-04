package pl.makrohard.alfacommerce.domain

import org.koin.dsl.bind
import org.koin.dsl.module
import pl.makrohard.alfacommerce.domain.model.Filters
import pl.makrohard.alfacommerce.domain.usecase.*

val domainModule = module {
    single<GetCategoriesUseCase> {
        GetCategoriesUseCaseImpl(get())
    } bind GetCategoriesUseCase::class

    single<GetProductsUseCase> {
        GetProductsUseCaseImpl(get())
    } bind GetProductsUseCase::class

    single<GetProductDetailsUseCase> {
        GetProductDetailsUseCaseImpl(get())
    } bind GetProductDetailsUseCase::class

    factory {
        Filters()
    }
}