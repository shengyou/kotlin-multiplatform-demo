package io.kraftsman.common.contracts

import io.kraftsman.common.ui.states.AppState
import io.kraftsman.common.ui.screens.Screen
import kotlinx.coroutines.flow.StateFlow

interface AppViewModelContract {
    val uiState: StateFlow<AppState>
    fun navigateTo(screen: Screen)
    fun signup(username: String, password: String)
    fun login(username: String, password: String, code: String)
}
