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
import io.kraftsman.common.ui.screens.partials.SignInCreateAccount
import io.kraftsman.common.ui.extensions.supportWideScreen

@Composable
fun HomeScreen(
    navToSignIn: () -> Unit,
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

            //TODO: update Branding component
            /*Branding(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_logo),
                text = StringResource.MOPCON,
            )*/

            Spacer(
                modifier = Modifier
                    .weight(1f, fill = true)
                    .animateContentSize()
            )

            SignInCreateAccount(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                navToSignIn = navToSignIn,
                onSignUp = onSignUp
            )
        }
    }
}
