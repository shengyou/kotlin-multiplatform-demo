package io.kraftsman.server.handlers

import io.kraftsman.common.clients.models.User
import io.kraftsman.common.clients.requests.UserLoginRequest
import io.kraftsman.common.clients.requests.UserSignupRequest
import io.kraftsman.common.clients.responses.UserResponse
import io.kraftsman.server.extensions.md5
import io.kraftsman.server.extensions.toResponse
import io.kraftsman.server.utilities.createQrCodeImgBytes
import io.kraftsman.server.utilities.createSecret
import io.kraftsman.server.utilities.verifyCode
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

private val dataMap = mutableMapOf<String, User>()

suspend fun list(call: ApplicationCall) = run {
    val users = dataMap.map { (email, user) ->
        UserResponse(
            email = email,
            createdAt = user.createdAt,
        )
    }
    call.respond(HttpStatusCode.OK, mapOf("users" to users))
}

suspend fun signup(call: ApplicationCall) =
    run {
        val userSignupRequest = call.receive<UserSignupRequest>()
        if (dataMap.containsKey(userSignupRequest.email)) {
            call.respond(HttpStatusCode.BadRequest, mapOf("message" to "Username Existed"))
        } else {
            dataMap[userSignupRequest.email] = User(
                email = userSignupRequest.email,
                passwordHash = userSignupRequest.password.md5(),
                secret = createSecret(),
                createdAt = Clock.System.now().toLocalDateTime(TimeZone.UTC),
            )

            call.respond(HttpStatusCode.OK, dataMap[userSignupRequest.email]!!.toResponse())
        }
    }

suspend fun qrcode(call: ApplicationCall) =
    run {
        val user = dataMap[call.request.queryParameters["email"]]!!

        val bytes = createQrCodeImgBytes(
            username = user.email,
            secret = user.secret,
        )

        call.respondBytes(
            bytes = bytes,
            contentType = ContentType.Image.PNG,
            status = HttpStatusCode.OK,
        )
    }

suspend fun login(call: ApplicationCall) =
    run {
        val userLoginRequest = call.receive<UserLoginRequest>()

        if (!dataMap.containsKey(userLoginRequest.email)) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = mapOf("message" to "User does not exist")
            )
        }

        val user = dataMap[userLoginRequest.email]!!
        if (userLoginRequest.password.md5() != user.passwordHash) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = mapOf("message" to "Username / Password Not Matched")
            )
        }

        if (!verifyCode(user.secret, userLoginRequest.authenticationCode)) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = mapOf("message" to "Wrong 2FA Code")
            )
        }

        call.respond(
            status = HttpStatusCode.OK,
            message = user.toResponse()
        )
    }
