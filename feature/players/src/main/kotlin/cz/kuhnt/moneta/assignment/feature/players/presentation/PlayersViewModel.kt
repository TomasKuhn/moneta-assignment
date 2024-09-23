package cz.kuhnt.moneta.assignment.feature.players.presentation

import cz.kuhnt.moneta.assignment.library.mvvm.presentation.AbstractViewModel

internal class PlayersViewModel : AbstractViewModel<PlayersViewModel.State>(State()) {

    data class State(val placeholder: String = "") : AbstractViewModel.State
}
