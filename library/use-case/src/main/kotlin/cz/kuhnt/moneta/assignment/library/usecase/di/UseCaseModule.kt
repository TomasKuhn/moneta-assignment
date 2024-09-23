package cz.kuhnt.moneta.assignment.library.usecase.di

import cz.kuhnt.moneta.assignment.library.usecase.domain.GoBackUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GoBackUseCase)
}
