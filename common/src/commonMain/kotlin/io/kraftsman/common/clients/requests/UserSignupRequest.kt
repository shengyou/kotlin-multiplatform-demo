package io.kraftsman.common.clients.requests

import kotlinx.serialization.Serializable

@Serializable
data class UserSignupRequest(
    val email: String,
    val password: String,
)
