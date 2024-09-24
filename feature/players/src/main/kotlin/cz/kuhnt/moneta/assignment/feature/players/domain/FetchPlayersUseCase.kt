package cz.kuhnt.moneta.assignment.feature.players.domain

import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.library.networking.data.Data
import cz.kuhnt.moneta.assignment.library.usecase.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

//TODO KDoc
internal class FetchPlayersUseCase(
    private val localPlayersRepository: LocalPlayersRepository,
    private val remotePlayersRepository: RemotePlayersRepository
) : SynchronousUseCase<Unit, Flow<Data<List<Player>>>> {

    override fun invoke(input: Unit): Flow<Data<List<Player>>> {
        val cursor = localPlayersRepository.cursor
        return remotePlayersRepository.fetchPlayers(cursor)
            .onEach {
                if (it is Data.Success) {
                    localPlayersRepository.setPlayers(
                        players = it.value,
                        cursor = cursor + 1
                    )
                }
            }
    }
}
