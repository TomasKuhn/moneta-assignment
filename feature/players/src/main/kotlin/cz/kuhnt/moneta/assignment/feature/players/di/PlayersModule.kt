package cz.kuhnt.moneta.assignment.feature.players.di

import cz.kuhnt.moneta.assignment.feature.players.presentation.PlayersViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val playersModule = module { 
    viewModelOf(::PlayersViewModel)
} 
