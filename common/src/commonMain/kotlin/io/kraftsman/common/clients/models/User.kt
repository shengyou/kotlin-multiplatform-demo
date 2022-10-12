package io.kraftsman.common.clients.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val passwordHash: String,
    val secret: String,
    val createdAt: LocalDateTime,
)
