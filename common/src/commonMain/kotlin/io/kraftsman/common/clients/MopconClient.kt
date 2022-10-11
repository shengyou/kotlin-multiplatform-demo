package io.kraftsman.common.clients

import io.kraftsman.common.requests.UserLoginRequest
import io.kraftsman.common.requests.UserSignupRequest
import io.kraftsman.common.responses.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json

private val apiBaseUrl = "http://localhost:8080"
val httpClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
    defaultRequest {
        url(apiBaseUrl)
    }
    expectSuccess = true
}

suspend fun signup(userSignupRequest: UserSignupRequest): UserResponse =
    httpClient.post("/users/signup") {
        setBody(userSignupRequest)
        contentType(ContentType.Application.Json)
    }.body()

suspend fun qrcode(username: String): ByteArray =
    httpClient.get("/users/qrcode") {
        parameter("username", username)
        contentType(ContentType.Image.PNG)
    }.body()

fun qrcodeUrl(username: String): String =
    "$apiBaseUrl/users/qrcode?username=$username"

suspend fun login(userLoginRequest: UserLoginRequest): UserResponse =
    httpClient.post("/users/login") {
        setBody(userLoginRequest)
        contentType(ContentType.Application.Json)
    }.body()
