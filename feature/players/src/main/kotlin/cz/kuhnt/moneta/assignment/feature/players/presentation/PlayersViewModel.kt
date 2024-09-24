package cz.kuhnt.moneta.assignment.feature.players.presentation

import androidx.lifecycle.viewModelScope
import cz.kuhnt.moneta.assignment.feature.players.domain.FetchPlayersUseCase
import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.library.mvvm.presentation.AbstractViewModel
import cz.kuhnt.moneta.assignment.library.networking.data.Data
import cz.kuhnt.moneta.assignment.library.usecase.domain.invoke
import kotlinx.coroutines.launch

internal class PlayersViewModel(
    private val fetchPlayersUseCase: FetchPlayersUseCase
) : AbstractViewModel<PlayersViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            fetchPlayersUseCase().collect(::onPlayersData)
        }
    }

    private fun onPlayersData(data: Data<List<Player>>) {
        state = when (data) {
            Data.Loading -> state.copy(isLoading = true)
            is Data.Error -> state.copy(
                isLoading = false,
                error = data.exception.message
            )

            is Data.Success -> state.copy(
                isLoading = false,
                error = null,
                playerNames = data.value.map { it.name },
            )
        }
    }

    data class State(
        val isLoading: Boolean = false,
        val playerNames: List<String> = emptyList(),
        val error: String? = null,
    ) : AbstractViewModel.State
}
