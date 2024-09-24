package cz.kuhnt.moneta.assignment.feature.players.presentation

import androidx.lifecycle.viewModelScope
import cz.kuhnt.moneta.assignment.feature.players.domain.ObservePlayerDetailUseCase
import cz.kuhnt.moneta.assignment.library.mvvm.presentation.AbstractViewModel
import cz.kuhnt.moneta.assignment.library.usecase.domain.GoBackUseCase
import cz.kuhnt.moneta.assignment.library.usecase.domain.invoke
import kotlinx.coroutines.launch

internal class PlayerDetailViewModel(
    observePlayerDetailUseCase: ObservePlayerDetailUseCase,
    private val goBack: GoBackUseCase
) : AbstractViewModel<PlayerDetailViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observePlayerDetailUseCase().collect { player ->
                requireNotNull(player) { "Player should not be null" }
                state = state.copy(
                    name = player.name,
                    height = player.height,
                    weight = player.weight,
                    college = player.college,
                    country = player.country,
                    position = player.position,
                    team = player.team.name
                )
            }
        }
    }

    fun onBack() {
        viewModelScope.launch {
            goBack()
        }
    }

    data class State(
        val name: String = "",
        val height: String = "",
        val weight: String = "",
        val college: String = "",
        val country: String = "",
        val position: String = "",
        val team: String = "",
    ) : AbstractViewModel.State
}
