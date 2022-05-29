package de.kgveinigkeitzschocken.db

import de.kgveinigkeitzschocken.core.inject.di
import de.kgveinigkeitzschocken.core.manager.EncryptionManager
import de.kgveinigkeitzschocken.core.manager.DateManager
import de.kgveinigkeitzschocken.core.util.getIntEnv
import de.kgveinigkeitzschocken.core.util.getStringEnv
import de.kgveinigkeitzschocken.db.entity.UserEntity
import de.kgveinigkeitzschocken.db.entity.UserTable
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.kodein.di.instance

private val encryptionManager: EncryptionManager by di.instance()
private val dateManager: DateManager by di.instance()

fun Application.initDB() {

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
                dateOfBirth = dateManager.getLocalDate(
                    getIntEnv("superuser.dateOfBirth.year"),
                    getIntEnv("superuser.dateOfBirth.month"),
                    getIntEnv("superuser.dateOfBirth.day")
                )
                isAdmin = true
            }
        }
    }
}