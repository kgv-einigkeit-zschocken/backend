package de.kgveinigkeitzschocken.db.entity

import de.kgveinigkeitzschocken.core.inject.di
import de.kgveinigkeitzschocken.core.manager.DateManager
import de.kgveinigkeitzschocken.core.model.User
import io.ktor.server.auth.Principal
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.varPop
import org.kodein.di.instance
import java.time.LocalDate

private val dateManager: DateManager by di.instance()

object UserTable : IntIdTable("users") {
    var firstName: Column<String> = varchar("first_name", 256)
    var lastName: Column<String> = varchar("last_name", 256)
    var username: Column<String> = varchar("username", 256)
    var emailAddress: Column<String> = varchar("email_address", 256)
    var password: Column<String> = text("password")
    var dateOfBirth: Column<LocalDate> = date("date_of_birth")
    var isAdmin: Column<Boolean> = bool("is_admin").default(false)
}

class UserEntity(id: EntityID<Int>) : IntEntity(id), Principal {
    companion object : IntEntityClass<UserEntity>(UserTable)

    var firstName by UserTable.firstName
    var lastName by UserTable.lastName
    var username by UserTable.username
    var emailAddress by UserTable.emailAddress
    var password by UserTable.password
    var dateOfBirth by UserTable.dateOfBirth
    var isAdmin by UserTable.isAdmin

    /**
     * Updates a user
     *
     * @param body The data which holds the data to update the user
     * @return [UserEntity]
     */
    fun update(body: User.Body.Update): UserEntity = transaction {
        body.firstName?.let {
            this@UserEntity.firstName = it
        }

        body.lastName?.let {
            this@UserEntity.lastName = it
        }

        body.emailAddress?.let {
            this@UserEntity.emailAddress = it
        }

        body.dateOfBirth?.let {
            this@UserEntity.dateOfBirth = dateManager.getLocalDate(it)
        }

        this@UserEntity
    }

    fun getResponse(): User.Response {
        return User.Response(
            id = this.id.value,
            firstName = firstName,
            lastName = lastName,
            username = username,
            emailAddress = emailAddress,
            dateOfBirth = dateManager.getTimestamp(dateOfBirth),
            isAdmin = isAdmin
        )
    }

}