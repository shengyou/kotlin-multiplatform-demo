package io.kraftsman.common.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.kraftsman.common.ui.components.Branding
import io.kraftsman.common.ui.screens.partials.WelcomeForm
import io.kraftsman.common.ui.extensions.supportWideScreen
import io.kraftsman.common.ui.locales.StringResource

@Composable
fun WelcomeScreen(
    navToLogin: () -> Unit,
    onSignUp: () -> Unit,
) {
    Surface(modifier = Modifier.supportWideScreen()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(
                modifier = Modifier
                    .weight(1f, fill = true)
                    .animateContentSize()
            )

            Branding(
                modifier = Modifier.fillMaxWidth(),
                text = StringResource.title,
            )

            Spacer(
                modifier = Modifier
                    .weight(1f, fill = true)
                    .animateContentSize()
            )

            WelcomeForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                navToLogin = navToLogin,
                onSignup = onSignUp
            )
        }
    }
}
