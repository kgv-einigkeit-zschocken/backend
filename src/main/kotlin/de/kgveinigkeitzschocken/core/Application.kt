package de.kgveinigkeitzschocken.core

import de.kgveinigkeitzschocken.api.routing.configureRouting
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureRouting()
}
