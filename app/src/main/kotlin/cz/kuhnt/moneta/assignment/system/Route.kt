package cz.kuhnt.moneta.assignment.system

internal enum class Route {
    Players,
    PlayerDetail;

    operator fun invoke() = name.lowercase()

    companion object {
        val Initial = Players
    }
}
