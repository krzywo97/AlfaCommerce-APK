package pl.makrohard.alfacommerce.data.repository

import org.koin.dsl.bind
import org.koin.dsl.module
import pl.makrohard.alfacommerce.domain.repository.CategoriesRepository
import pl.makrohard.alfacommerce.domain.repository.ColorsRepository
import pl.makrohard.alfacommerce.domain.repository.ProductsRepository

val repositoryModule = module {

    //TODO: figure out how to lazy load those classes
    single<CategoriesRepository> {
        CategoriesRepositoryImpl()
    } bind CategoriesRepository::class

    single<ColorsRepository> {
        ColorsRepositoryImpl()
    } bind ColorsRepository::class

    single<ProductsRepository> {
        ProductsRepositoryImpl()
    } bind ProductsRepository::class
}