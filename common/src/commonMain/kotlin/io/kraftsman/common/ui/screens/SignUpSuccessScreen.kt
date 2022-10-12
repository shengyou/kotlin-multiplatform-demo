package io.kraftsman.common.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.kraftsman.common.ui.components.QrCode
import io.kraftsman.common.ui.extensions.supportWideScreen
import io.kraftsman.common.ui.locales.StringResource

@Composable
fun SignUpSuccessScreen(
    qrcodeUrl: String,
    navToSignIn: () -> Unit,
) {
    Surface(modifier = Modifier.supportWideScreen()) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Your 2FA QR Code",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 24.dp)
                    .fillMaxWidth()
            )

            QrCode(
                url = qrcodeUrl,
            )

            Button(
                onClick = navToSignIn,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 28.dp, bottom = 3.dp)
            ) {
                Text(
                    text = StringResource.signIn,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}
