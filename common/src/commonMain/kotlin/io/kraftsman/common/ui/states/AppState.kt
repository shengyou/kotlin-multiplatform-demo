package io.kraftsman.common.ui.states

import androidx.compose.runtime.Immutable
import io.kraftsman.common.ui.screens.Screen

@Immutable
data class AppState(
    val currentScreen: Screen = Screen.Welcome,
    val qrcodeUrl: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AppState

        if (currentScreen != other.currentScreen) return false
        if (qrcodeUrl != other.qrcodeUrl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = currentScreen.hashCode()
        result = 31 * result + qrcodeUrl.hashCode()
        return result
    }
}
