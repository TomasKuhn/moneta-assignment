package cz.kuhnt.moneta.assignment.feature.players.domain

import cz.kuhnt.moneta.assignment.feature.players.model.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class MemoryPlayersRepository : LocalPlayersRepository {

    private val players = MutableStateFlow<List<Player>>(emptyList())
    private val playerDetailId = MutableStateFlow<Long?>(null)

    override var cursor = 0

    override fun observePlayers() = players.asStateFlow()

    override fun setPlayers(players: List<Player>, cursor: Int) {
        this.players.update {
            it + players
        }
        this.cursor = cursor
    }

    override fun setPlayerDetailId(id: Long?) {
        playerDetailId.value = id
    }

    override fun observePlayerDetail(): Flow<Player?> {
        return playerDetailId.flatMapLatest { id ->
            players.map {
                it.firstOrNull { player -> player.id == id }
            }
        }
    }
}
