package de.kgveinigkeitzschocken.core.plugins

import de.kgveinigkeitzschocken.api.userRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.response.respond

fun Application.configureRouting() {
    install(Routing)

    routing {
        route("/api") {
            route("/v1") {
                userRouting()
            }
        }
    }

}