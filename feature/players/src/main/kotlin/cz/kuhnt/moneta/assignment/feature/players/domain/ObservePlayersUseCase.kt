package cz.kuhnt.moneta.assignment.feature.players.domain

import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.library.usecase.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Observes the players from the local repository.
 */
internal class ObservePlayersUseCase(
    private val localPlayersRepository: LocalPlayersRepository
) : SynchronousUseCase<Unit, Flow<List<Player>>> {

    override fun invoke(input: Unit): Flow<List<Player>> {
        return localPlayersRepository.observePlayers()
    }
}
