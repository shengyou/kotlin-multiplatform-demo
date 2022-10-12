package io.kraftsman.common.contracts

import io.kraftsman.common.clients.requests.UserLoginRequest
import io.kraftsman.common.clients.requests.UserSignupRequest
import io.kraftsman.common.clients.responses.UserResponse

interface MopconClientContract {
    suspend fun signup(userSignupReqDTO: UserSignupRequest): UserResponse
    suspend fun qrcode(username: String): ByteArray
    fun qrcodeUrl(username: String): String
    suspend fun login(userLoginReqDTO: UserLoginRequest): UserResponse
}
