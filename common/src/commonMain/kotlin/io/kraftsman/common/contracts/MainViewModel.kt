package io.kraftsman.common.contracts

import io.kraftsman.common.ui.states.MainUiState
import io.kraftsman.common.ui.states.NavDestinations
import kotlinx.coroutines.flow.StateFlow

interface MainViewModel {
    val uiState: StateFlow<MainUiState>
    fun nav(navDestination: NavDestinations)
    fun signUp(username: String, password: String)
    fun signIn(username: String, password: String, code: String)
}
