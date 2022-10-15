package io.kraftsman.desktop.ui.containers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.kraftsman.common.ui.screens.*
import io.kraftsman.desktop.viewmodels.AppViewModel

@Composable
fun AppContainer(viewModel: AppViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState.currentScreen) {
        Screen.Welcome -> WelcomeScreen(
            navToLogin = { viewModel.navigateTo(Screen.Login) },
            onSignUp = { viewModel.navigateTo(Screen.Signup) }
        )

        Screen.Login -> LoginScreen(
            onLogin = viewModel::login,
            onBackPressed = { viewModel.navigateTo(Screen.Welcome) }
        )

        Screen.Signup -> SignupScreen(
            onSignup = viewModel::signup,
            onBackPressed = { viewModel.navigateTo(Screen.Welcome) }
        )

        Screen.AuthenticationCode -> AuthenticationCodeScreen(
            qrcodeUrl = uiState.qrcodeUrl,
            navToLogin = { viewModel.navigateTo(Screen.Login) }
        )

        Screen.Home -> HomeScreen()
    }
}
