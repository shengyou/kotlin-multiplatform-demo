package io.kraftsman.common.clients.requests

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val email: String,
    val password: String,
    val authenticationCode: String,
)
