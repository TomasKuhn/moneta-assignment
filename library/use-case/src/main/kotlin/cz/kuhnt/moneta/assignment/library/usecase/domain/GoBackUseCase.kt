package cz.kuhnt.moneta.assignment.library.usecase.domain

class GoBackUseCase internal constructor(
    private val navigationController: GoBackNavigationController
) : SynchronousUseCase<Unit, Unit> {

    override fun invoke(input: Unit) = navigationController.goBack()
}
