package io.kraftsman.android.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.kraftsman.android.viewmodels.AppViewModel
import io.kraftsman.common.contracts.MopconClientContract

class AppViewModelFactory(
    private val client: MopconClientContract
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AppViewModel(client) as T
    }
}
