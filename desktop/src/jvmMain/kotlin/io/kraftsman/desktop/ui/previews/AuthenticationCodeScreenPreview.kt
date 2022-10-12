package io.kraftsman.desktop.ui.previews

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.kraftsman.common.ui.screens.AuthenticationCodeScreen

@Preview
@Composable
fun AuthenticationCodeScreenPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AuthenticationCodeScreen(
                "https://api.qrserver.com/v1/create-qr-code/?size==350x350&data=MOPCON",
                { },
            )
        }
    }
}
