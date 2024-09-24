package cz.kuhnt.moneta.assignment.device

import cz.kuhnt.moneta.assignment.model.NavigationEvent
import kotlinx.coroutines.flow.Flow

internal interface MainNavigationController {
    val navigationEvent: Flow<NavigationEvent>
}
