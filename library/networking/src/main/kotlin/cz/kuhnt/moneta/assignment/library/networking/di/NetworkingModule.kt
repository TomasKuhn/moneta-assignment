package cz.kuhnt.moneta.assignment.library.networking.di

import cz.kuhnt.moneta.assignment.library.networking.data.ApiHandler
import cz.kuhnt.moneta.assignment.library.networking.data.PlayersApi
import cz.kuhnt.moneta.assignment.library.networking.system.RetrofitFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

val networkingModule = module {
    singleOf(::ApiHandler)
    single { RetrofitFactory.createRetrofit() }
    single { get<Retrofit>().create(PlayersApi::class.java) }
}
