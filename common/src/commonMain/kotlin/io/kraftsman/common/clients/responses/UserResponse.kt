package io.kraftsman.common.clients.responses

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val email: String,
    val createdAt: LocalDateTime,
)
