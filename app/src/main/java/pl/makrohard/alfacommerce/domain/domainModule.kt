package pl.makrohard.alfacommerce.domain

import org.koin.dsl.bind
import org.koin.dsl.module
import pl.makrohard.alfacommerce.domain.model.Filters
import pl.makrohard.alfacommerce.domain.usecase.GetCategoriesUseCase
import pl.makrohard.alfacommerce.domain.usecase.GetCategoriesUseCaseImpl
import pl.makrohard.alfacommerce.domain.usecase.GetProductsUseCase
import pl.makrohard.alfacommerce.domain.usecase.GetProductsUseCaseImpl

val domainModule = module {
    single<GetCategoriesUseCase> {
        GetCategoriesUseCaseImpl(get())
    } bind GetCategoriesUseCase::class

    single<GetProductsUseCase> {
        GetProductsUseCaseImpl(get())
    } bind GetProductsUseCase::class

    factory {
        Filters()
    }
}