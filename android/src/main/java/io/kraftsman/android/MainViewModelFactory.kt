package io.kraftsman.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.kraftsman.common.contracts.RestApi

class MainViewModelFactory(
    private val api: RestApi
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModelImpl(api) as T
    }
}
