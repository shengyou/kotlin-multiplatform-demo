package io.kraftsman.android.ui.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.kraftsman.android.ui.theme.MopconTheme
import io.kraftsman.common.ui.screens.LoginScreen

@Preview
@Composable
fun LoginScreenPreview() {
    MopconTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginScreen(
                fun(_: String, _: String, _: String) {},
                { },
            )
        }
    }
}
