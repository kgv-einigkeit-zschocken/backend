package de.kgveinigkeitzschocken.db.service

import de.kgveinigkeitzschocken.core.exceptions.EntityNotFoundException
import de.kgveinigkeitzschocken.core.model.User
import de.kgveinigkeitzschocken.db.entity.UserEntity
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class UserService {

    /**
     * Queries all available users
     *
     * @return [List]<[UserEntity]>
     */
    fun all(): List<UserEntity> = transaction {
        UserEntity.all().toList()
    }

    /**
     * Tries to find a user by its user ID
     *
     * @param userID The ID of the user to find - [Int]
     * @return [UserEntity]
     *
     * @throws [EntityNotFoundException] if no user has been found for the passed user ID
     */
    @Throws(EntityNotFoundException::class)
    fun findByID(userID: Int): UserEntity = transaction {
        UserEntity.findById(userID) ?: throw EntityNotFoundException()
    }

    /**
     * Creates a new user
     *
     * @param body The body which holds the data of the new user
     * @return [UserEntity]
     */
    fun create(body: User.Body.Create): UserEntity = transaction {
        UserEntity.new {
            firstName = body.firstName
            lastName = body.lastName
            username = body.username
            emailAddress = body.emailAddress
            password = body.password
            dateOfBirth = LocalDate.ofInstant(
                Instant.ofEpochSecond(body.dateOfBirth),
                ZoneId.systemDefault()
            )
            isAdmin = body.isAdmin
        }
    }

}