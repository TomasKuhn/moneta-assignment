package cz.kuhnt.moneta.assignment.library.networking.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PlayersApi {

    // Authorization id should be handled in OkHttpClient interceptor.
    // OkHttpClient should be injected to RetrofitFactory.createRetrofit().
    @Headers("Authorization: 829fdb64-1a3c-463a-9136-1ea13971c96b")
    @GET("v1/players")
    suspend fun getPlayers(
        @Query("cursor") cursor: Int,
        @Query("per_page") perPage: Int
    ): Response<PlayersDto>
}
