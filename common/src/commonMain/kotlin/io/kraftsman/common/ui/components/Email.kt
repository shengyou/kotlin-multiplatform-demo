package io.kraftsman.common.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import io.kraftsman.common.ui.states.EmailState
import io.kraftsman.common.ui.states.TextFieldState
import io.kraftsman.common.ui.locales.StringResource

@Composable
fun Email(
    emailState: TextFieldState = remember { EmailState() },
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: () -> Unit = {},
) {
    OutlinedTextField(
        value = emailState.text,
        singleLine = true,
        onValueChange = {
            emailState.text = it
        },
        label = {
            Text(
                text = StringResource.email,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                emailState.onFocusChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    emailState.enableShowErrors()
                }
            },
        textStyle = MaterialTheme.typography.bodyMedium,
        isError = emailState.showErrors(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        ),
    )

    emailState.getError()?.let { error -> TextFieldError(textError = error) }
}
