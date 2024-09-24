package cz.kuhnt.moneta.assignment.feature.players.model

data class Team(
    val id: Long,
    val name: String,
    val conference: String,
    val division: String,
    val city: String,
    val fullName: String,
    val abbreviation: String
)
