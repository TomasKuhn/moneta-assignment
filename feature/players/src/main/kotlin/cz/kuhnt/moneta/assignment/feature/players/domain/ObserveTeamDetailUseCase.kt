package cz.kuhnt.moneta.assignment.feature.players.domain

import cz.kuhnt.moneta.assignment.feature.players.model.Team
import cz.kuhnt.moneta.assignment.library.usecase.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Observes the team detail from the local repository.
 */
internal class ObserveTeamDetailUseCase(
    private val localTeamsRepository: LocalTeamsRepository
) : SynchronousUseCase<Unit, Flow<Team?>> {

    override fun invoke(input: Unit): Flow<Team?> {
        return localTeamsRepository.observeTeamDetail()
    }
}
