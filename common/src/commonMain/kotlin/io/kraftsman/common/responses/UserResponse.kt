package io.kraftsman.common.responses

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val username: String,
    val createDate: LocalDateTime,
)
