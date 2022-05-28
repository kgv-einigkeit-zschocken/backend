package de.kgveinigkeitzschocken.db

import de.kgveinigkeitzschocken.core.inject.di
import de.kgveinigkeitzschocken.core.manager.EncryptionManager
import de.kgveinigkeitzschocken.core.util.getIntEnv
import de.kgveinigkeitzschocken.core.util.getStringEnv
import de.kgveinigkeitzschocken.db.entities.UserEntity
import de.kgveinigkeitzschocken.db.entities.UserTable
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.kodein.di.instance
import java.time.LocalDate

fun Application.initDB() {
    val encryptionManager: EncryptionManager by di.instance()

    Database.connect(
        url = getStringEnv("db.jdbcUrl"),
        driver = getStringEnv("db.driverClassName"),
        user = getStringEnv("db.username"),
        password = getStringEnv("db.password")
    )

    transaction {
        SchemaUtils.create(UserTable)
    }

    transaction {
        if (UserEntity.find { UserTable.isAdmin eq true }.count() < 1L) {
            UserEntity.new {
                firstName = getStringEnv("superuser.firstName")
                lastName = getStringEnv("superuser.lastName")
                username = getStringEnv("superuser.username")
                emailAddress = getStringEnv("superuser.emailAddress")
                password =  encryptionManager.encrypt(
                    getStringEnv("superuser.password")
                )
                dateOfBirth = LocalDate.of(
                    getIntEnv("superuser.dateOfBirth.year"),
                    getIntEnv("superuser.dateOfBirth.month"),
                    getIntEnv("superuser.dateOfBirth.day")
                )
                isAdmin = true
            }
        }
    }
}