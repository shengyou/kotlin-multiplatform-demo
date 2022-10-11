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
import io.kraftsman.common.ui.states.VerificationCodeState

@Composable
fun SignInContent(
    onSignIn: (String, String, String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val focusRequester = remember { FocusRequester() }
        val emailState by rememberSaveable(stateSaver = EmailStateSaver) {
            mutableStateOf(EmailState())
        }
        val passwordState = remember { PasswordState() }
        val codeState = remember { VerificationCodeState() }
        val onSubmit = {
            if (emailState.isValid && passwordState.isValid && codeState.isValid) {
                onSignIn(emailState.text, passwordState.text, codeState.text)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Email(emailState = emailState, imeAction = ImeAction.Done, onImeAction = onSubmit)
        Spacer(modifier = Modifier.height(16.dp))
        Password(
            label = StringResource.password,
            passwordState = passwordState,
            modifier = Modifier.focusRequester(focusRequester),
            onImeAction = { onSubmit() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Password(
            label = StringResource.verificationCode,
            passwordState = codeState,
            modifier = Modifier.focusRequester(focusRequester),
            onImeAction = { onSubmit() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onSubmit() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            enabled = emailState.isValid && passwordState.isValid && codeState.isValid
        ) {
            Text(
                text = StringResource.signIn
            )
        }
    }
}
