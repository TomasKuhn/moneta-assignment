package cz.kuhnt.moneta.assignment.library.networking.di

import cz.kuhnt.moneta.assignment.library.networking.data.PlayersApi
import cz.kuhnt.moneta.assignment.library.networking.system.RetrofitFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkingModule = module {
    single {
        RetrofitFactory.createRetrofit()
        Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(PlayersApi::class.java) }
}
