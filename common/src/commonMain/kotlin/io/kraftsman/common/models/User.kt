package io.kraftsman.common.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val passwordHash: String,
    val secret: String,
    val createDate: LocalDateTime,
)
