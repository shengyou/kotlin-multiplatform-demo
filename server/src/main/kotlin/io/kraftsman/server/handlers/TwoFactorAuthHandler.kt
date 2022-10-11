package io.kraftsman.server.handlers

import io.kraftsman.common.models.User
import io.kraftsman.common.requests.UserLoginRequest
import io.kraftsman.common.requests.UserSignupRequest
import io.kraftsman.common.responses.UserResponse
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
    val users = dataMap.map { (username, user) ->
        UserResponse(
            username = username,
            createDate = user.createDate,
        )
    }
    call.respond(HttpStatusCode.OK, mapOf("users" to users))
}

suspend fun signup(call: ApplicationCall) =
    run {
        val userSignupRequest = call.receive<UserSignupRequest>()
        if (dataMap.containsKey(userSignupRequest.username)) {
            call.respond(HttpStatusCode.BadRequest, mapOf("message" to "Username Existed"))
        } else {
            dataMap[userSignupRequest.username] = User(
                username = userSignupRequest.username,
                passwordHash = userSignupRequest.password.md5(),
                secret = createSecret(),
                createDate = Clock.System.now().toLocalDateTime(TimeZone.UTC),
            )

            call.respond(HttpStatusCode.OK, dataMap[userSignupRequest.username]!!.toResponse())
        }
    }

suspend fun qrcode(call: ApplicationCall) =
    run {
        val user = dataMap[call.request.queryParameters["username"]]!!

        val bytes = createQrCodeImgBytes(
            username = user.username,
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

        if (!dataMap.containsKey(userLoginRequest.username)) {
            call.respond(HttpStatusCode.BadRequest, mapOf("message" to "User does not exist"))
        }

        val user = dataMap[userLoginRequest.username]!!
        if (userLoginRequest.password.md5() != user.passwordHash) {
            call.respond(HttpStatusCode.BadRequest, mapOf("message" to "Username / Password Not Matched"))
        }

        if (!verifyCode(user.secret, userLoginRequest.code)) {
            call.respond(HttpStatusCode.BadRequest, mapOf("message" to "Wrong 2FA Code"))
        }

        call.respond(HttpStatusCode.OK, user.toResponse())
    }
