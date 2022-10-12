package io.kraftsman.server.extensions

import io.kraftsman.common.clients.models.User
import io.kraftsman.common.clients.responses.UserResponse
import java.security.MessageDigest
import java.util.Base64

fun String.md5(): String =
    MessageDigest.getInstance("MD5")
        .also {
            it.update(toByteArray())
        }.run {
            String(Base64.getEncoder().encode(digest()))
        }

fun User.toResponse(): UserResponse =
    UserResponse(
        email = email,
        createdAt = createdAt,
    )
