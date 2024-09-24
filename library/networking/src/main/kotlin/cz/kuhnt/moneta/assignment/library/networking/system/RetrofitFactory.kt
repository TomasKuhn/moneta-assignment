package cz.kuhnt.moneta.assignment.library.networking.system

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitFactory {

    private const val BASE_URL = "https://api.balldontlie.io/"

    fun createRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) // Note: This is only placeholder URL which is replaced by [EnvironmentInterceptor]
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
