package cz.kuhnt.moneta.assignment.library.networking.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayersApi {
    
    @GET("v1/players")
    suspend fun getPlayers(
        @Path("cursor") cursor: Int,
        @Path("per_page") perPage: Int
    ): Response<List<PlayerDto>>
}
