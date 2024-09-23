package cz.kuhnt.moneta.assignment.di

import cz.kuhnt.moneta.assignment.presentation.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


internal val mainModule = module {
    viewModelOf(::MainViewModel)
}
