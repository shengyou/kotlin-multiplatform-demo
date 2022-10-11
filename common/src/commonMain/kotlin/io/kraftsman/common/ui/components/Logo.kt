package io.kraftsman.common.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp


@Composable
fun Logo(painter: Painter) {
    Image(
        painter = painter,
        modifier = Modifier.padding(horizontal = 76.dp)
            .clip(CircleShape)
            .size(160.dp),
        contentDescription = null
    )
}
