package io.kraftsman.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import io.kraftsman.android.MainViewModelFactory
import io.kraftsman.common.ui.screens.HomeScreen
import io.kraftsman.common.ui.screens.SignInScreen
import io.kraftsman.common.ui.screens.SignUpScreen
import io.kraftsman.common.ui.screens.SignUpSuccessScreen
import io.kraftsman.common.ui.screens.WelcomeScreen
import io.kraftsman.common.ui.states.NavDestinations
import io.kraftsman.android.MainViewModelImpl
import io.kraftsman.common.clients.RestApiImpl

@Composable
fun MOPCON2022App() {
    val viewModel: MainViewModelImpl = viewModel(
        factory = MainViewModelFactory(
            RestApiImpl(
                urlString = "https://9287-220-128-121-166.jp.ngrok.io"
            )
        )
    )
    val uiState by viewModel.uiState.collectAsState()

    when (uiState.currentScreen) {
        NavDestinations.Home -> HomeScreen(
            navToSignIn = { viewModel.nav(NavDestinations.SignIn) },
            onSignUp = { viewModel.nav(NavDestinations.SignUp) }
        )

        NavDestinations.SignIn -> SignInScreen(
            onSignIn = viewModel::signIn,
            onBackPressed = { viewModel.nav(NavDestinations.Home) }
        )

        NavDestinations.SignUp -> SignUpScreen(
            onSignUp = viewModel::signUp,
            onBackPressed = { viewModel.nav(NavDestinations.Home) }
        )

        NavDestinations.SignUpSucceed -> SignUpSuccessScreen(
            byteArray = uiState.qrCode,
            navToSignIn = { viewModel.nav(NavDestinations.SignIn) }
        )

        NavDestinations.Welcome -> WelcomeScreen()
    }
}
