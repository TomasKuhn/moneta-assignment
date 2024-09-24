package cz.kuhnt.moneta.assignment.feature.players.domain

import cz.kuhnt.moneta.assignment.feature.players.model.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MemoryTeamsRepository : LocalTeamsRepository {
    
    private val _teamDetail = MutableStateFlow<Team?>(null)
    
    override fun observeTeamDetail() = _teamDetail.asStateFlow()

    override fun setTeamDetail(team: Team?) {
        _teamDetail.value = team
    }
}
