package io.kraftsman.desktop.viewmodels

import io.kraftsman.common.contracts.AppViewModelContract
import io.kraftsman.common.contracts.MopconClientContract
import io.kraftsman.common.clients.requests.UserLoginRequest
import io.kraftsman.common.clients.requests.UserSignupRequest
import io.kraftsman.common.ui.states.AppState
import io.kraftsman.common.ui.screens.Screen
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewModel(
    private val client: MopconClientContract,
) : AppViewModelContract {
    private val mainScope = MainScope()
    private val _uiState = MutableStateFlow(AppState())
    override val uiState: StateFlow<AppState> = _uiState.stateIn(
        mainScope,
        SharingStarted.WhileSubscribed(5_000),
        AppState()
    )

    override fun navigateTo(screen: Screen) {
        _uiState.value = _uiState.value.copy(
            currentScreen = screen
        )
    }

    override fun signup(username: String, password: String) {
        mainScope.launch {
            kotlin.runCatching {
                client.signup(
                    UserSignupRequest(
                        email = username,
                        password = password
                    )
                )
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    qrcodeUrl = client.qrcodeUrl(username)
                )
                navigateTo(Screen.AuthenticationCode)
            }.onFailure {
                println(it.toString())
            }
        }
    }

    override fun login(username: String, password: String, code: String) {
        mainScope.launch {
            kotlin.runCatching {
                client.login(
                    UserLoginRequest(
                        email = username,
                        password = password,
                        authenticationCode = code
                    )
                )
            }.onSuccess {
                navigateTo(Screen.Home)
            }.onFailure {
                println(it.toString())
            }
        }
    }
}
