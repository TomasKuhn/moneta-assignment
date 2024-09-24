package cz.kuhnt.moneta.assignment.library.networking.data

sealed class Data<out T> {
    data object Loading : Data<Nothing>()
    data class Success<out T>(val value: T) : Data<T>()
    data class Error(val exception: Throwable) : Data<Nothing>()
}
