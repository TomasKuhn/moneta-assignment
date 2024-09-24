package cz.kuhnt.moneta.assignment.di

import cz.kuhnt.moneta.assignment.device.GlobalNavigationController
import cz.kuhnt.moneta.assignment.device.MainNavigationController
import cz.kuhnt.moneta.assignment.domain.ObserveNavigationEventsUseCase
import cz.kuhnt.moneta.assignment.feature.players.domain.PlayersNavigationController
import cz.kuhnt.moneta.assignment.library.usecase.domain.GoBackNavigationController
import cz.kuhnt.moneta.assignment.presentation.MainViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.binds
import org.koin.dsl.module

internal val mainModule = module {
    viewModelOf(::MainViewModel)

    singleOf(::GlobalNavigationController) binds (
        arrayOf(
            GoBackNavigationController::class,
            MainNavigationController::class,
            PlayersNavigationController::class
        )
        )

    factoryOf(::ObserveNavigationEventsUseCase)
}
