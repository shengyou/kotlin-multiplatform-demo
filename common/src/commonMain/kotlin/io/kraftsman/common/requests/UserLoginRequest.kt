package io.kraftsman.common.requests

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val username: String,
    val password: String,
    val code: String,
)
