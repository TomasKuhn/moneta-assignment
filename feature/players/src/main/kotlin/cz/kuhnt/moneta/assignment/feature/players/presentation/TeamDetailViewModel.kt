package cz.kuhnt.moneta.assignment.feature.players.presentation

import androidx.lifecycle.viewModelScope
import cz.kuhnt.moneta.assignment.feature.players.domain.ObserveTeamDetailUseCase
import cz.kuhnt.moneta.assignment.library.mvvm.presentation.AbstractViewModel
import cz.kuhnt.moneta.assignment.library.usecase.domain.GoBackUseCase
import cz.kuhnt.moneta.assignment.library.usecase.domain.invoke
import kotlinx.coroutines.launch

internal class TeamDetailViewModel(
    observeTeamDetail: ObserveTeamDetailUseCase,
    private val goBack: GoBackUseCase
) : AbstractViewModel<TeamDetailViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observeTeamDetail().collect { team ->
                requireNotNull(team) { "Team should not be null" }
                state = state.copy(
                    name = team.name,
                    conference = team.conference,
                    division = team.division,
                    city = team.city,
                    fullName = team.fullName,
                    abbreviation = team.abbreviation,
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
        val conference: String = "",
        val division: String = "",
        val city: String = "",
        val fullName: String = "",
        val abbreviation: String = "",
    ) : AbstractViewModel.State
}
