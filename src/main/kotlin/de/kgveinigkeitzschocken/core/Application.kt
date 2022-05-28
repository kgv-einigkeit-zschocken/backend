package de.kgveinigkeitzschocken.core

import de.kgveinigkeitzschocken.api.routing.initRouting
import de.kgveinigkeitzschocken.db.initDB
import io.ktor.server.application.Application

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    initRouting()
    initDB()
}
