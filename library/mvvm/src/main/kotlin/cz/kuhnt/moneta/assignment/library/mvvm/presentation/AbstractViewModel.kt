package cz.kuhnt.moneta.assignment.library.mvvm.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class AbstractViewModel<S : AbstractViewModel.State>(initialState: S) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    protected var state: S
        get() = _state.value
        set(value) {
            _state.value = value
        }

    interface State
}
