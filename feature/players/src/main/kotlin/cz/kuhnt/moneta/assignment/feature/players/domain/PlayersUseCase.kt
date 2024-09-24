package cz.kuhnt.moneta.assignment.feature.players.domain

import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.library.networking.data.Data
import cz.kuhnt.moneta.assignment.library.usecase.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

internal class FetchPlayersUseCase(
    private val remotePlayersRepository: RemotePlayersRepository
) : SynchronousUseCase<Unit, Flow<Data<List<Player>>>> {

    override fun invoke(input: Unit): Flow<Data<List<Player>>> {
        return remotePlayersRepository.fetchPlayers()
    }
}
