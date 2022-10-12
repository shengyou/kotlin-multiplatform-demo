package io.kraftsman.common.ui.screens.partials

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import io.kraftsman.common.ui.components.Email
import io.kraftsman.common.ui.components.Password
import io.kraftsman.common.ui.locales.StringResource
import io.kraftsman.common.ui.states.ConfirmPasswordState
import io.kraftsman.common.ui.states.EmailState
import io.kraftsman.common.ui.states.PasswordState
import io.kraftsman.common.ui.theme.stronglyDeemphasizedAlpha

@Composable
fun SignupForm(
    onSubmit: (String, String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val passwordFocusRequest = remember { FocusRequester() }
        val confirmationPasswordFocusRequest = remember { FocusRequester() }

        val emailState = remember { EmailState() }
        val passwordState = remember { PasswordState() }
        val confirmPasswordState = remember { ConfirmPasswordState(passwordState = passwordState) }

        Email(
            emailState = emailState,
            onImeAction = {
                passwordFocusRequest.requestFocus()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Password(
            label = StringResource.password,
            passwordState = passwordState,
            imeAction = ImeAction.Next,
            onImeAction = {
                confirmationPasswordFocusRequest.requestFocus()
            },
            modifier = Modifier.focusRequester(passwordFocusRequest)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Password(
            label = StringResource.confirmPassword,
            passwordState = confirmPasswordState,
            onImeAction = {
                onSubmit(emailState.text, passwordState.text)
            },
            modifier = Modifier.focusRequester(confirmationPasswordFocusRequest)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = StringResource.termsAndConditions,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
                .copy(alpha = stronglyDeemphasizedAlpha)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onSubmit(emailState.text, passwordState.text) },
            modifier = Modifier.fillMaxWidth(),
            enabled = emailState.isValid &&
                    passwordState.isValid &&
                    confirmPasswordState.isValid
        ) {
            Text(text = StringResource.createAccount)
        }
    }
}
