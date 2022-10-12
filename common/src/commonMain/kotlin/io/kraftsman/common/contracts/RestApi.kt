package io.kraftsman.common.contracts

import io.kraftsman.common.requests.UserLoginRequest
import io.kraftsman.common.requests.UserSignupRequest
import io.ktor.client.statement.*

interface RestApi {
    suspend fun signup(userSignupReqDTO: UserSignupRequest): HttpResponse
    fun qrcodeUrl(username: String): String
    suspend fun login(userLoginReqDTO: UserLoginRequest): HttpResponse
}
