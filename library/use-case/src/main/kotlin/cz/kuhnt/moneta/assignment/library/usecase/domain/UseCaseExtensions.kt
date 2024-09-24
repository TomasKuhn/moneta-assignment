package cz.kuhnt.moneta.assignment.library.usecase.domain

operator fun <O> SynchronousUseCase<Unit, O>.invoke(): O = invoke(Unit)

suspend operator fun <O> SuspendUseCase<Unit, O>.invoke(): O = invoke(Unit)
