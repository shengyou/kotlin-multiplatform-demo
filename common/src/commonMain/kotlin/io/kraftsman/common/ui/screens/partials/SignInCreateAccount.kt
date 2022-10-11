package io.kraftsman.common.ui.screens.partials

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.kraftsman.common.ui.components.OrSignUp
import io.kraftsman.common.ui.locales.StringResource
import io.kraftsman.common.ui.theme.stronglyDeemphasizedAlpha

@Composable
fun SignInCreateAccount(
    modifier: Modifier = Modifier,
    navToSignIn: () -> Unit,
    onSignUp: () -> Unit
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = StringResource.signInCreateAccount,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = stronglyDeemphasizedAlpha),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 64.dp, bottom = 12.dp)
        )

        Button(
            onClick = navToSignIn,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, bottom = 3.dp)
        ) {
            Text(
                text = StringResource.signIn,
                style = MaterialTheme.typography.titleSmall
            )
        }
        OrSignUp(
            onSignUp = onSignUp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
