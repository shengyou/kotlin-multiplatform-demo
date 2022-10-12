package io.kraftsman.common.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.kraftsman.common.R

@Composable
actual fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.mopcon_logo),
        modifier = Modifier.padding(horizontal = 76.dp)
            .clip(CircleShape)
            .size(160.dp),
        contentDescription = null
    )
}
