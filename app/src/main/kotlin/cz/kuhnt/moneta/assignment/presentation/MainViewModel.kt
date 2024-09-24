package cz.kuhnt.moneta.assignment.presentation

import androidx.lifecycle.viewModelScope
import cz.kuhnt.moneta.assignment.domain.ObserveNavigationEventsUseCase
import cz.kuhnt.moneta.assignment.library.mvvm.presentation.AbstractViewModel
import cz.kuhnt.moneta.assignment.library.usecase.domain.invoke
import cz.kuhnt.moneta.assignment.model.NavigationEvent
import cz.kuhnt.moneta.assignment.system.Route
import kotlinx.coroutines.launch

internal class MainViewModel(
    private val observeNavigationEvents: ObserveNavigationEventsUseCase
) : AbstractViewModel<MainViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observeNavigationEvents().collect { navigationEvent ->
                state = state.copy(navigationEvent = navigationEvent)
            }
        }
    }

    fun onNavigationEventConsumed() {
        state = state.copy(navigationEvent = null)
    }

    data class State(
        val navigationEvent: NavigationEvent? = NavigationEvent.ForwardEvent(Route.Initial),
    ) : AbstractViewModel.State
}
