package de.kgveinigkeitzschocken.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
            fun passwordMatchesPasswordConfirmation(): Boolean {
                return password == passwordConfirmation
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
        )
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