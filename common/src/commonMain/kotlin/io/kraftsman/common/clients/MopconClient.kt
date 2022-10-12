package io.kraftsman.common.clients

import io.kraftsman.common.contracts.MopconClientContract
import io.kraftsman.common.clients.requests.UserLoginRequest
import io.kraftsman.common.clients.requests.UserSignupRequest
import io.kraftsman.common.clients.responses.UserResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

class MopconClient(
    private val urlString: String = "https://7124-36-231-127-172.jp.ngrok.io",
) : MopconClientContract {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url(urlString)
        }
        expectSuccess = true
    }

    override suspend fun signup(userSignupRequest: UserSignupRequest): UserResponse =
        httpClient.post("/users/signup") {
            setBody(userSignupRequest)
            contentType(ContentType.Application.Json)
        }.body()

    override suspend fun qrcode(email: String): ByteArray =
        httpClient.get("/users/qrcode") {
            parameter("email", email)
            contentType(ContentType.Image.PNG)
        }.body()

    override fun qrcodeUrl(email: String): String =
        "$urlString/users/qrcode?email=$email"

    override suspend fun login(userLoginRequest: UserLoginRequest): UserResponse =
        httpClient.post("/users/login") {
            setBody(userLoginRequest)
            contentType(ContentType.Application.Json)
        }.body()
}
