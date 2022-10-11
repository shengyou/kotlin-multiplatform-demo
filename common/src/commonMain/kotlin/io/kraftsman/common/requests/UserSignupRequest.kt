package io.kraftsman.common.requests

import kotlinx.serialization.Serializable

@Serializable
data class UserSignupRequest(
    val username: String,
    val password: String,
)
