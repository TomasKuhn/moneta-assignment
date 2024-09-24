package cz.kuhnt.moneta.assignment.feature.players.data

import cz.kuhnt.moneta.assignment.feature.players.domain.RemotePlayersRepository
import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.library.networking.data.ApiHandler
import cz.kuhnt.moneta.assignment.library.networking.data.Data
import cz.kuhnt.moneta.assignment.library.networking.data.PlayersApi
import kotlinx.coroutines.flow.Flow

internal class RetrofitPlayersRepository(
    private val apiHandler: ApiHandler,
    private val api: PlayersApi
) : RemotePlayersRepository {

    override fun fetchPlayers(cursor: Int): Flow<Data<List<Player>>> {
        return apiHandler.request(
            callApi = { api.getPlayers(cursor, PLAYERS_PER_PAGE) },
            parseDto = { players.map { it.toModel() } }
        )
    }

    companion object {
        private const val PLAYERS_PER_PAGE = 35
    }
}
