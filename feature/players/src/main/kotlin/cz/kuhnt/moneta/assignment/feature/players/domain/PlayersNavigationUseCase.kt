package cz.kuhnt.moneta.assignment.feature.players.domain

import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.feature.players.model.Team
import cz.kuhnt.moneta.assignment.library.usecase.domain.SynchronousUseCase

internal class OpenPlayerDetailUseCase(
    private val localPlayersRepository: LocalPlayersRepository,
    private val navigationController: PlayersNavigationController
) : SynchronousUseCase<Player, Unit> {

    override fun invoke(input: Player) {
        localPlayersRepository.setPlayerDetailId(input.id)
        navigationController.goToPlayerDetail()
    }
}

internal class OpenTeamDetailUseCase(
    private val localTeamsRepository: LocalTeamsRepository,
    private val navigationController: PlayersNavigationController
) : SynchronousUseCase<Team, Unit> {

    override fun invoke(input: Team) {
        localTeamsRepository.setTeamDetail(input)
        navigationController.goToTeamDetail()
    }
}
