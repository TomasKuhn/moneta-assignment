package cz.kuhnt.moneta.assignment.feature.players.data

import cz.kuhnt.moneta.assignment.feature.players.model.Team
import cz.kuhnt.moneta.assignment.library.networking.data.TeamDto

internal fun TeamDto.toModel() = Team(
    id = id,
    name = name,
    conference = conference,
    division = division,
    city = city,
    fullName = fullName,
    abbreviation = abbreviation
) 
