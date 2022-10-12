package io.kraftsman.common.clients.requests

import kotlinx.serialization.Serializable

@Serializable
data class UserSignupRequest(
    val username: String,
    val password: String,
)
