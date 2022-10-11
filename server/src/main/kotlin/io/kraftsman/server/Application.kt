package io.kraftsman.server

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.kraftsman.server.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureHome()
        configureTwoFactorAuth()
    }.start(wait = true)
}
