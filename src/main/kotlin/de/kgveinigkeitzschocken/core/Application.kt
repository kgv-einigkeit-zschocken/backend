package de.kgveinigkeitzschocken.core

import de.kgveinigkeitzschocken.core.plugins.configureContentNegotiation
import de.kgveinigkeitzschocken.core.plugins.configureResources
import de.kgveinigkeitzschocken.core.plugins.configureRouting
import de.kgveinigkeitzschocken.core.plugins.configureStatusPages
import de.kgveinigkeitzschocken.db.configureDatabase
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureContentNegotiation()
    configureResources()
    configureStatusPages()
    configureRouting()
    configureDatabase()
}