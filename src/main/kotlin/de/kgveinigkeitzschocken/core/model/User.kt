package de.kgveinigkeitzschocken.core.model

import de.kgveinigkeitzschocken.core.exceptions.DateInFutureException
import de.kgveinigkeitzschocken.core.exceptions.EmailAddressAlreadyTakenException
import de.kgveinigkeitzschocken.core.exceptions.FieldAbsentException
import de.kgveinigkeitzschocken.core.exceptions.PasswordConfirmationIncorrectException
import de.kgveinigkeitzschocken.core.exceptions.UsernameAlreadyTakenException
import de.kgveinigkeitzschocken.core.inject.di
import de.kgveinigkeitzschocken.db.service.UserService
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.kodein.di.instance

val userService: UserService by di.instance()

class User {

    class Body {
        @Serializable
        data class Create(
            @SerialName("first_name")
            val firstName: String,

            @SerialName("last_name")
            val lastName: String,

            @SerialName("username")
            val username: String,

            @SerialName("email_address")
            val emailAddress: String,

            @SerialName("password")
            val password: String,

            @SerialName("password_confirmation")
            val passwordConfirmation: String,

            @SerialName("date_of_birth")
            val dateOfBirth: Long,

            @SerialName("is_admin")
            val isAdmin: Boolean
        ) {
            init {
                if (
                    firstName.isBlank() ||
                    lastName.isBlank() ||
                    username.isBlank() ||
                    emailAddress.isBlank() ||
                    password.isBlank() ||
                    passwordConfirmation.isBlank()
                ) {
                    throw FieldAbsentException()
                }

                if (password != passwordConfirmation) {
                    throw PasswordConfirmationIncorrectException()
                }

                if (userService.usernameAlreadyTaken(username)) {
                    throw UsernameAlreadyTakenException()
                }

                if (userService.emailAddressAlreadyTaken(emailAddress)) {
                    throw EmailAddressAlreadyTakenException()
                }

                if (dateOfBirth > System.currentTimeMillis()) {
                    throw DateInFutureException()
                }
            }
        }

        @Serializable
        data class Update(
            @SerialName("first_name")
            val firstName: String? = null,

            @SerialName("last_name")
            val lastName: String? = null,

            @SerialName("email_address")
            val emailAddress: String? = null,

            @SerialName("date_of_birth")
            val dateOfBirth: Long? = null,

            @SerialName("is_admin")
            val isAdmin: Boolean? = null
        ) {
            init {
                firstName?.let {
                    if (it.isBlank()) {
                        throw FieldAbsentException()
                    }
                }

                lastName?.let {
                    if (it.isBlank()) {
                        throw FieldAbsentException()
                    }
                }

                emailAddress?.let {
                    if (it.isBlank()) {
                        throw FieldAbsentException()
                    }

                    if (userService.emailAddressAlreadyTaken(emailAddress)) {
                        throw EmailAddressAlreadyTakenException()
                    }
                }

                dateOfBirth?.let {
                    if (it > System.currentTimeMillis()) {
                        throw DateInFutureException()
                    }
                }
            }

        }
    }

    @Serializable
    data class Response(
        @SerialName("id")
        val id: Int,

        @SerialName("first_name")
        val firstName: String,

        @SerialName("last_name")
        val lastName: String,

        @SerialName("username")
        val username: String,

        @SerialName("email_address")
        val emailAddress: String,

        @SerialName("date_of_birth")
        val dateOfBirth: Long,

        @SerialName("is_admin")
        val isAdmin: Boolean
    )

}