package io.kraftsman.server.plugins

import io.kraftsman.server.handlers.list
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import io.kraftsman.server.handlers.signup
import io.kraftsman.server.handlers.qrcode
import io.kraftsman.server.handlers.login

fun Application.configureTwoFactorAuth() {
    routing {
        install(ContentNegotiation) {
            json()
        }

        get("/users/list") {
            list(call)
        }

        post("/users/signup") {
            signup(call)
        }

        get("/users/qrcode") {
            qrcode(call)
        }

        post("/users/login") {
            login(call)
        }
    }
}
