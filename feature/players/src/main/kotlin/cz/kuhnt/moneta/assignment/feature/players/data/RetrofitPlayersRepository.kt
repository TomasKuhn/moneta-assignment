package cz.kuhnt.moneta.assignment.feature.players.data

import cz.kuhnt.moneta.assignment.feature.players.domain.RemotePlayersRepository
import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.library.networking.data.Data
import cz.kuhnt.moneta.assignment.library.networking.data.PlayersApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class RetrofitPlayersRepository(private val api: PlayersApi) : RemotePlayersRepository {

    override fun fetchPlayers(): Flow<Data<List<Player>>> {
        return flow {
            emit(Data.Loading)
            val response = api.getPlayers(0, 30) // Use real values
            if (response.isSuccessful) {
                //TODO Create global solution
                response.body()?.let { playersDto ->
                    emit(Data.Success(playersDto.map { it.toModel() }))
                }
            } else {
                emit(Data.Error(Exception("Http error: ${response.code()}")))
            }
        }
    }
}
