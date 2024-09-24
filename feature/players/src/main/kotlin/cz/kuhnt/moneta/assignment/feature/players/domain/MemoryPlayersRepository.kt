package cz.kuhnt.moneta.assignment.feature.players.domain

import cz.kuhnt.moneta.assignment.feature.players.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MemoryPlayersRepository : LocalPlayersRepository {
    
    private val players = MutableStateFlow<List<Player>>(emptyList())
    
    override var cursor = 0

    override fun observePlayers() = players.asStateFlow()
    
    override fun setPlayers(players: List<Player>, cursor: Int) {
        this.players.update { 
            it + players   
        }
        this.cursor = cursor
    }
    
}
