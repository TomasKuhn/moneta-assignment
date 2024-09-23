package cz.kuhnt.moneta.assignment.library.usecase.domain

sealed interface UseCase<in I, out O>

interface SynchronousUseCase<in I, out O> : UseCase<I, O> {
    operator fun invoke(input: I): O
}

interface SuspendUseCase<in I, out O> : UseCase<I, O> {
    suspend operator fun invoke(input: I): O
}
