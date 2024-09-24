package cz.kuhnt.moneta.assignment.feature.players.data

import cz.kuhnt.moneta.assignment.feature.players.model.Player
import cz.kuhnt.moneta.assignment.library.networking.data.PlayerDto

internal fun PlayerDto.toModel() = Player(
    id = id,
    name = "$firstName $lastName",
    position = position,
    height = height,
    weight = weight,
    college = college,
    country = country,
    team = team.toModel()
)

