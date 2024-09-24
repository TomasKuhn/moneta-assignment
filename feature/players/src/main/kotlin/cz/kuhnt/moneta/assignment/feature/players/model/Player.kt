package cz.kuhnt.moneta.assignment.feature.players.model

data class Player(
    val id: Long,
    val name: String,
    val position: String,
    val height: String,
    val weight : String,
    val college: String,
    val country: String,
    val team: Team,
)
