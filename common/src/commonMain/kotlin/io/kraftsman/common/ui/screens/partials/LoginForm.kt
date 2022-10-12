package io.kraftsman.common.ui.screens.partials

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import io.kraftsman.common.ui.components.Email
import io.kraftsman.common.ui.components.Password
import io.kraftsman.common.ui.locales.StringResource
import io.kraftsman.common.ui.states.EmailState
import io.kraftsman.common.ui.states.EmailStateSaver
import io.kraftsman.common.ui.states.PasswordState
import io.kraftsman.common.ui.states.AuthenticationCodeState

@Composable
fun LoginForm(
    onSubmit: (String, String, String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val passwordFocusRequest = remember { FocusRequester() }
        val authenticationCodeFocusRequest = remember { FocusRequester() }

        val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
            mutableStateOf(EmailState())
        }
        val passwordState = remember { PasswordState() }
        val codeState = remember { AuthenticationCodeState() }

        val onSubmit = {
            if (emailState.isValid && passwordState.isValid && codeState.isValid) {
                onSubmit(emailState.text, passwordState.text, codeState.text)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
            modifier = Modifier.focusRequester(passwordFocusRequest),
            onImeAction = {
                authenticationCodeFocusRequest.requestFocus()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Password(
            label = StringResource.authenticationCode,
            passwordState = codeState,
            modifier = Modifier.focusRequester(authenticationCodeFocusRequest),
            onImeAction = {
                onSubmit()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onSubmit()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            enabled = emailState.isValid &&
                    passwordState.isValid &&
                    codeState.isValid
        ) {
            Text(
                text = StringResource.login
            )
        }
    }
}
