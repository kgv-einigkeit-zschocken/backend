package de.kgveinigkeitzschocken.core

import de.kgveinigkeitzschocken.api.initRouting
import de.kgveinigkeitzschocken.db.initDB
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.resources.Resources
import io.ktor.server.routing.Routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(Resources)
    install(Routing)
    install(ContentNegotiation) {
        json()
    }

    initRouting()
    initDB()
}
