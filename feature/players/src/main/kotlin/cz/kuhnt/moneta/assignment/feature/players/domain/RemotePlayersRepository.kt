package cz.kuhnt.moneta.assignment.feature.players.domain

import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.library.networking.data.Data
import kotlinx.coroutines.flow.Flow

interface RemotePlayersRepository {

    fun fetchPlayers(cursor: Int): Flow<Data<List<Player>>>
}
