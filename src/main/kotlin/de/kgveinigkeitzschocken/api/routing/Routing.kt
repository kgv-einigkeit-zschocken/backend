package de.kgveinigkeitzschocken.api.routing

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.respond

fun Application.initRouting() {
    routing {
        get("/") {
            call.respond("Hello, world!")
        }
    }
}
