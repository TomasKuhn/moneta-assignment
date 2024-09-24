package cz.kuhnt.moneta.assignment.feature.players.data

import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.library.networking.data.PlayerDto

internal fun PlayerDto.toModel() = Player(
    name = "$firstName $lastName",
    position = position,
    team = team.toModel()
)

