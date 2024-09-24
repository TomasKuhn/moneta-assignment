package cz.kuhnt.moneta.assignment.feature.players.domain

import cz.kuhnt.moneta.assignment.feature.players.model.Team
import kotlinx.coroutines.flow.Flow

interface LocalTeamsRepository {
    fun observeTeamDetail(): Flow<Team?>
    fun setTeamDetail(team: Team?)
}
