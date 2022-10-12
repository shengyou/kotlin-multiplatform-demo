package io.kraftsman.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.lazyPainterResource

@Composable
fun AuthenticationCode(
    url: String
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        KamelImage(
            resource = lazyPainterResource(
                data = url,
                filterQuality = FilterQuality.High,
            ),
            contentDescription = "",
            modifier = Modifier
                .size(256.dp)
                .background(Color.White, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillWidth,
            onLoading = { CircularProgressIndicator(it) },
            onFailure = {
                println(it.cause)
                println(it.message)
            },
        )
    }
}
