package cz.kuhnt.moneta.assignment.library.mvvm.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class AbstractViewModel<S : AbstractViewModel.State>(initialState: S) : ViewModel() {
    private val _states = MutableStateFlow(initialState)
    val states: StateFlow<S> = _states.asStateFlow()
    
    protected var state: S
        get() = _states.value
        set(value) {
            _states.value = value
        }

    interface State
}
