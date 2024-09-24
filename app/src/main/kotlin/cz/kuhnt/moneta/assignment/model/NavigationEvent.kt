package cz.kuhnt.moneta.assignment.model

import cz.kuhnt.moneta.assignment.system.Route

internal sealed interface NavigationEvent {
    
    data class ForwardEvent(val route: Route) : NavigationEvent
    
    data object BackEvent : NavigationEvent
}
