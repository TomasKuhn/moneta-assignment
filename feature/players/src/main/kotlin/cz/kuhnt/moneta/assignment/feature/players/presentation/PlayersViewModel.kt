package cz.kuhnt.moneta.assignment.feature.players.presentation

import androidx.lifecycle.viewModelScope
import cz.kuhnt.moneta.assignment.feature.players.domain.FetchPlayersUseCase
import cz.kuhnt.moneta.assignment.feature.players.domain.ObservePlayersUseCase
import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.library.mvvm.presentation.AbstractViewModel
import cz.kuhnt.moneta.assignment.library.networking.data.Data
import cz.kuhnt.moneta.assignment.library.usecase.domain.invoke
import kotlinx.coroutines.launch

internal class PlayersViewModel(
    private val fetchPlayers: FetchPlayersUseCase,
    private val observePlayers: ObservePlayersUseCase,
) : AbstractViewModel<PlayersViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observePlayers().collect { players ->
                state = state.copy(players = players)
            }
        }
        onFetchPlayers()
    }
    
    fun onFetchPlayers() {
        viewModelScope.launch {
            fetchPlayers().collect { data ->
                state = when (data) {
                    Data.Loading -> state.copy(
                        isLoading = true
                    )
                    is Data.Error -> state.copy(
                        isLoading = false,
                        error = data.exception.message
                    )
                    is Data.Success -> state.copy(
                        isLoading = false,
                        error = null,
                    )
                }
            }
        }
    }

    fun onErrorDismiss() {
        state = state.copy(error = null)
    }

    data class State(
        val isLoading: Boolean = false,
        val players: List<Player> = emptyList(),
        val error: String? = null,
    ) : AbstractViewModel.State
}
