package cz.kuhnt.moneta.assignment.di

import cz.kuhnt.moneta.assignment.feature.players.di.playersModule
import cz.kuhnt.moneta.assignment.library.networking.di.networkingModule

internal val appModules = listOf(
    mainModule,
    networkingModule,
    playersModule,
)
