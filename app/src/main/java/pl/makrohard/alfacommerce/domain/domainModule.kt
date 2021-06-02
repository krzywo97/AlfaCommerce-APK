package pl.makrohard.alfacommerce.domain

import org.koin.dsl.bind
import org.koin.dsl.module
import pl.makrohard.alfacommerce.domain.usecase.GetCategoriesUseCase
import pl.makrohard.alfacommerce.domain.usecase.GetCategoriesUseCaseImpl

val domainModule = module {
    single<GetCategoriesUseCase> {
        GetCategoriesUseCaseImpl(get())
    } bind GetCategoriesUseCase::class
}