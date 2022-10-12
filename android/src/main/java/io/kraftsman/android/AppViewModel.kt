package io.kraftsman.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.kraftsman.common.contracts.AppViewModelContract
import io.kraftsman.common.contracts.MopconClientContract
import io.kraftsman.common.clients.requests.UserLoginRequest
import io.kraftsman.common.clients.requests.UserSignupRequest
import io.kraftsman.common.ui.states.AppState
import io.kraftsman.common.ui.screens.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewModel(
    private val client: MopconClientContract,
) : ViewModel(), AppViewModelContract {
    private val _uiState = MutableStateFlow(AppState())
    override val uiState: StateFlow<AppState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        AppState()
    )

    override fun navigateTo(screen: Screen) {
        _uiState.value = _uiState.value.copy(
            currentScreen = screen
        )
    }

    override fun signup(username: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                client.signup(
                    UserSignupRequest(
                        username = username,
                        password = password
                    )
                )
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    qrcodeUrl = client.qrcodeUrl(username)
                )
                navigateTo(Screen.AuthenticationCode)
            }.onFailure {
                Log.e("MainViewModel", it.toString())
            }
        }
    }

    override fun login(username: String, password: String, code: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                client.login(
                    UserLoginRequest(
                        username = username,
                        password = password,
                        code = code
                    )
                )
            }.onSuccess {
                navigateTo(Screen.Home)
            }.onFailure {
                Log.e("MainViewModel", it.toString())
            }
        }
    }
}
