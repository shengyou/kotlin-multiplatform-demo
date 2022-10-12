package io.kraftsman.desktop.ui.containers

import androidx.compose.runtime.*
import io.kraftsman.common.clients.MopconClient
import io.kraftsman.common.clients.requests.UserLoginRequest
import io.kraftsman.common.clients.requests.UserSignupRequest
import io.kraftsman.common.ui.screens.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun AppContainer() {
    val mainScope = MainScope()
    val apiClient = MopconClient()

    var currentScreen by remember { mutableStateOf(Screen.Welcome) }
    var currentUsername by remember { mutableStateOf("") }

    when (currentScreen) {
        Screen.Welcome -> WelcomeScreen(
            navToLogin = { currentScreen = Screen.Login },
            onSignUp = { currentScreen = Screen.Signup }
        )

        Screen.Login -> LoginScreen(
            onLogin = { username, password, authenticationCode ->
                currentUsername = username
                mainScope.launch {
                    runBlocking {
                        apiClient.login(
                            UserLoginRequest(
                                email = username,
                                password = password,
                                authenticationCode = authenticationCode,
                            )
                        )
                        currentScreen = Screen.Home
                    }
                }
            },
            onBackPressed = { currentScreen = Screen.Welcome }
        )

        Screen.Signup -> SignupScreen(
            onSignup = { username, password ->
                currentUsername = username
                mainScope.launch {
                    runBlocking {
                        apiClient.signup(
                            UserSignupRequest(
                                email = username,
                                password = password,
                            )
                        )
                        currentScreen = Screen.AuthenticationCode
                    }
                }
            },
            onBackPressed = { currentScreen = Screen.Welcome }
        )

        Screen.AuthenticationCode -> AuthenticationCodeScreen(
            qrcodeUrl = apiClient.qrcodeUrl(currentUsername),
            navToLogin = { currentScreen = Screen.Login }
        )

        Screen.Home -> HomeScreen()
    }
}
