package cz.kuhnt.moneta.assignment.domain

import cz.kuhnt.moneta.assignment.device.MainNavigationController
import cz.kuhnt.moneta.assignment.library.usecase.domain.SynchronousUseCase
import cz.kuhnt.moneta.assignment.model.NavigationEvent
import kotlinx.coroutines.flow.Flow

internal class ObserveNavigationEventsUseCase(
    private val mainNavigationController: MainNavigationController
) : SynchronousUseCase<Unit, Flow<NavigationEvent>> {

    override fun invoke(input: Unit) = mainNavigationController.navigationEvent
}
