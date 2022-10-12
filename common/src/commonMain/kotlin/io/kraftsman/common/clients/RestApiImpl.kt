package io.kraftsman.common.clients

import io.kraftsman.common.contracts.RestApi
import io.kraftsman.common.requests.UserLoginRequest
import io.kraftsman.common.requests.UserSignupRequest
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

class RestApiImpl(
    private val urlString: String,
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

    override fun qrcodeUrl(username: String): String =
        "$urlString/users/qrcode?username=$username"

    override suspend fun login(userLoginReqDTO: UserLoginRequest): HttpResponse =
        httpClient.post("/users/login") {
            setBody(userLoginReqDTO)
            contentType(ContentType.Application.Json)
        }
}
