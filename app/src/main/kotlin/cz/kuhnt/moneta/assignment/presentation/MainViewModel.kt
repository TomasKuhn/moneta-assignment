package cz.kuhnt.moneta.assignment.presentation

import cz.kuhnt.moneta.assignment.library.mvvm.presentation.AbstractViewModel

internal class MainViewModel : AbstractViewModel<MainViewModel.State>(State()) {

    data class State(val placeholder: String = "") : AbstractViewModel.State
}
