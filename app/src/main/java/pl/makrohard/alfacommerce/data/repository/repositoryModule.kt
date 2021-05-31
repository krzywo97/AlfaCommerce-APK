package pl.makrohard.alfacommerce.data.repository

import org.koin.dsl.module

val repositoryModule = module {

    single {
        CategoriesRepositoryImpl()
    }

    single {
        ColorsRepositoryImpl()
    }

    single {
        ProductsRepositoryImpl()
    }
}