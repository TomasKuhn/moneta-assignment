package cz.kuhnt.moneta.assignment.di

import cz.kuhnt.moneta.assignment.feature.players.di.playersModule

internal val appModules = listOf(
    mainModule,
    playersModule,
)
