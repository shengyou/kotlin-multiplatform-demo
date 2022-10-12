package io.kraftsman.common.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.kraftsman.common.ui.components.TopBar
import io.kraftsman.common.ui.locales.StringResource
import io.kraftsman.common.ui.screens.partials.SignupForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    onSignup: (String, String) -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                topAppBarText = StringResource.createAccount,
                onBackPressed = onBackPressed
            )
        },
        content = { contentPadding ->
            Spacer(modifier = Modifier.height(44.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
                    .padding(horizontal = 20.dp)
            ) {
                Column {
                    SignupForm(
                        onSubmit = onSignup
                    )
                }
            }
        }
    )
}
