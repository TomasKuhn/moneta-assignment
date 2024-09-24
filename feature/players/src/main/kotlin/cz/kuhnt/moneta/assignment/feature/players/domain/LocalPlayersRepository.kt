package cz.kuhnt.moneta.assignment.feature.players.domain

import cz.kuhnt.moneta.assignment.feature.players.model.Player
import kotlinx.coroutines.flow.Flow

interface LocalPlayersRepository {
    val cursor: Int
    fun observePlayers() : Flow<List<Player>>
    fun setPlayers(players: List<Player>, cursor: Int)
}
