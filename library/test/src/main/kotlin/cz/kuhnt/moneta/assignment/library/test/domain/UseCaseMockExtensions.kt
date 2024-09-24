package cz.kuhnt.moneta.assignment.library.test.domain

import cz.kuhnt.moneta.assignment.library.usecase.domain.SynchronousUseCase
import io.mockk.every
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

inline fun <reified I : Any, O, reified UC : SynchronousUseCase<I, O>> UC.returns(value: O) {
    every { this@returns(any()) } returns value
}

inline fun <reified I : Any, O, reified UC : SynchronousUseCase<I, Flow<O>>> UC.returnsFlow(vararg value: O) {
    returns(flowOf(*value))
}
