package io.kraftsman.android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.kraftsman.common.contracts.MainViewModel
import io.ktor.util.InternalAPI
import io.ktor.util.toByteArray
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import io.kraftsman.common.contracts.RestApi
import io.kraftsman.common.requests.UserLoginRequest
import io.kraftsman.common.requests.UserSignupRequest
import io.kraftsman.common.ui.states.MainUiState
import io.kraftsman.common.ui.states.NavDestinations

class MainViewModelImpl(
    private val api: RestApi
) : ViewModel(), MainViewModel {
    private val _uiState = MutableStateFlow(MainUiState())
    override val uiState: StateFlow<MainUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        MainUiState()
    )

    override fun nav(navDestination: NavDestinations) {
        _uiState.value = _uiState.value.copy(
            currentScreen = navDestination
        )
    }

    override fun signUp(username: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                api.signup(
                    UserSignupRequest(
                        username = username,
                        password = password
                    )
                )
            }.onSuccess {
                qrcode(username)
            }.onFailure {
                Log.e("MainViewModel", it.toString())
            }
        }
    }

    @OptIn(InternalAPI::class)
    override fun qrcode(username: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                api.qrcode(
                    username = username
                )
            }.onSuccess {
                _uiState.value = _uiState.value.copy(
                    qrCode = it.content.toByteArray()
                )
                nav(NavDestinations.SignUpSucceed)
            }.onFailure {
                Log.e("MainViewModel", it.toString())
            }
        }
    }

    override fun signIn(username: String, password: String, code: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                api.login(
                    UserLoginRequest(
                        username = username,
                        password = password,
                        code = code
                    )
                )
            }.onSuccess {
                nav(NavDestinations.Welcome)
            }.onFailure {
                Log.e("MainViewModel", it.toString())
            }
        }
    }
}
