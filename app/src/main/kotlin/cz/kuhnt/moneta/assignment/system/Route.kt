package cz.kuhnt.moneta.assignment.system

internal enum class Route {
    Players;

    operator fun invoke() = name.lowercase()

    companion object {
        val Initial = Players
    }
}
