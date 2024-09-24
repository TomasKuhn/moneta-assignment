package cz.kuhnt.moneta.assignment.library.test.domain

import cz.kuhnt.moneta.assignment.library.usecase.domain.SuspendUseCase
import cz.kuhnt.moneta.assignment.library.usecase.domain.SynchronousUseCase
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

inline fun <reified I : Any, O, reified UC : SynchronousUseCase<I, O>> UC.on(input: I) = every { this@on(input) }

inline infix fun <reified I : Any, reified UC : SynchronousUseCase<I, Unit>> UC.just(runs: Runs): UC {
    every { this@just(any()) } just runs
    return this
}

inline fun <reified I : Any, O, reified UC : SynchronousUseCase<I, O>> UC.returns(value: O) {
    every { this@returns(any()) } returns value
}

inline fun <reified I : Any, O, reified UC : SynchronousUseCase<I, Flow<O>>> UC.returnsFlow(vararg value: O) {
    returns(flowOf(*value))
}

inline fun <reified I : Any, O, reified UC : SynchronousUseCase<I, Flow<O>>> UC.returnsEmptyFlow() {
    returns(emptyFlow())
}

inline fun <reified I : Any, O, reified UC : SuspendUseCase<I, O>> UC.on(input: I) = coEvery { this@on(input) }

inline infix fun <reified I : Any, reified UC : SuspendUseCase<I, Unit>> UC.just(runs: Runs): UC {
    coEvery { this@just(any()) } just runs
    return this
}

inline fun <reified I : Any, O, reified UC : SuspendUseCase<I, O>> UC.returns(value: O) {
    coEvery { this@returns(any()) } returns value
}

inline fun <reified I : Any, O, reified UC : SuspendUseCase<I, Flow<O>>> UC.returnsFlow(vararg value: O) {
    returns(flowOf(*value))
}

inline fun <reified I : Any, O, reified UC : SuspendUseCase<I, Flow<O>>> UC.returnsEmptyFlow() {
    returns(emptyFlow())
}
