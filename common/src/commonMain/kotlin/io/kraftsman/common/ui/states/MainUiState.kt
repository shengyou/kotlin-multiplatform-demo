package io.kraftsman.common.ui.states

import androidx.compose.runtime.Immutable

@Immutable
data class MainUiState(
    val currentScreen: NavDestinations = NavDestinations.Home,
    val qrCode: ByteArray = byteArrayOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MainUiState

        if (currentScreen != other.currentScreen) return false
        if (!qrCode.contentEquals(other.qrCode)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = currentScreen.hashCode()
        result = 31 * result + qrCode.contentHashCode()
        return result
    }
}
