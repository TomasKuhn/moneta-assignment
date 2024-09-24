package cz.kuhnt.moneta.assignment.feature.players.di

import cz.kuhnt.moneta.assignment.feature.players.data.RetrofitPlayersRepository
import cz.kuhnt.moneta.assignment.feature.players.domain.FetchPlayersUseCase
import cz.kuhnt.moneta.assignment.feature.players.domain.RemotePlayersRepository
import cz.kuhnt.moneta.assignment.feature.players.presentation.PlayersViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val playersModule = module {
    viewModelOf(::PlayersViewModel)
    
    singleOf(::RetrofitPlayersRepository) bind RemotePlayersRepository::class
    
    factoryOf(::FetchPlayersUseCase)
} 
