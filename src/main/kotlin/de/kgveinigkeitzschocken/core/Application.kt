package de.kgveinigkeitzschocken.core

import de.kgveinigkeitzschocken.api.routing.initRouting
import de.kgveinigkeitzschocken.db.initDB
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.resources.Resources
import io.ktor.server.routing.Routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(Resources)
    install(Routing)

    initRouting()
    initDB()
}
