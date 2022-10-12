package io.kraftsman.android.ui.containers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import io.kraftsman.android.viewmodels.AppViewModel
import io.kraftsman.android.factories.AppViewModelFactory
import io.kraftsman.common.clients.MopconClient
import io.kraftsman.common.ui.screens.*

@Composable
fun AppContainer() {
    val viewModel: AppViewModel = viewModel(
        factory = AppViewModelFactory(MopconClient())
    )
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
