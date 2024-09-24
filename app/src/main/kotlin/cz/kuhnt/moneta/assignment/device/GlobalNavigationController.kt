package cz.kuhnt.moneta.assignment.device

import cz.kuhnt.moneta.assignment.feature.players.domain.PlayersNavigationController
import cz.kuhnt.moneta.assignment.library.usecase.domain.GoBackNavigationController
import cz.kuhnt.moneta.assignment.model.NavigationEvent
import cz.kuhnt.moneta.assignment.system.Route
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

internal class GlobalNavigationController
    : MainNavigationController,
    GoBackNavigationController,
    PlayersNavigationController {

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = EXTRA_BUFFER_CAPACITY)
    override val navigationEvent = _navigationEvent.asSharedFlow()

    override fun goBack() = goTo(NavigationEvent.BackEvent)

    override fun goToPlayerDetail() = goTo(NavigationEvent.ForwardEvent(Route.PlayerDetail))

    private fun goTo(navigationEvent: NavigationEvent) {
        _navigationEvent.tryEmit(navigationEvent)
    }

    companion object {
        private const val EXTRA_BUFFER_CAPACITY = 5
    }
}
