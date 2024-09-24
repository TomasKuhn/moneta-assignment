package cz.kuhnt.moneta.assignment.di

import cz.kuhnt.moneta.assignment.feature.players.di.playersModule
import cz.kuhnt.moneta.assignment.library.networking.di.networkingModule
import cz.kuhnt.moneta.assignment.library.usecase.di.useCaseModule

internal val appModules = listOf(
    mainModule,
    networkingModule,
    playersModule,
    useCaseModule
)
