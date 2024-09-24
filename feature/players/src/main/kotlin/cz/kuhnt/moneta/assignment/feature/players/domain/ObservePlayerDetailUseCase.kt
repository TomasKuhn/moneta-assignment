package cz.kuhnt.moneta.assignment.feature.players.domain

import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.library.usecase.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

/**
 * Observes the player detail from the local repository.
 */
internal class ObservePlayerDetailUseCase(
    private val localPlayersRepository: LocalPlayersRepository
) : SynchronousUseCase<Unit, Flow<Player?>> {

    override fun invoke(input: Unit): Flow<Player?> {
        return localPlayersRepository.observePlayerDetail()
    }
}
