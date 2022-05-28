package de.kgveinigkeitzschocken.db

import de.kgveinigkeitzschocken.core.util.getEnvironmentVariable
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.initDB() {
    val jdbcUrl = getEnvironmentVariable("db.jdbcUrl")
    val driverClassName = getEnvironmentVariable("db.driverClassName")
    val username = getEnvironmentVariable("db.username")
    val password = getEnvironmentVariable("db.password")

    Database.connect(
        url = jdbcUrl,
        driver = driverClassName,
        user = username,
        password = password
    )

    transaction {
        // TODO: Create schemas
    }

    transaction {
        // TODO: Create superuser
    }
}