package io.kraftsman.common.clients

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.kraftsman.common.contracts.RestApi
import io.kraftsman.common.requests.UserLoginRequest
import io.kraftsman.common.requests.UserSignupRequest

class RestApiImpl(
    private val urlString: String
) : RestApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url(urlString)
        }
        expectSuccess = true
    }

    override suspend fun signup(userSignupReqDTO: UserSignupRequest): HttpResponse =
        httpClient.post("/users/signup") {
            setBody(userSignupReqDTO)
            contentType(ContentType.Application.Json)
        }

    override suspend fun qrcode(username: String): HttpResponse =
        httpClient.get("/users/qrcode") {
            parameter("username", username)
            contentType(ContentType.Image.PNG)
        }

    override suspend fun login(userLoginReqDTO: UserLoginRequest): HttpResponse =
        httpClient.post("/users/login") {
            setBody(userLoginReqDTO)
            contentType(ContentType.Application.Json)
        }
}
