package de.kgveinigkeitzschocken.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class ApiError {

    object ErrorCode {
        const val SERIALIZATION_FAILED = "SERIALIZATION_FAILED"
        const val ENTITY_NOT_FOUND = "ENTITY_NOT_FOUND"
        const val FIELD_ABSENT = "FIELD_ABSENT"

        const val DATE_IN_FUTURE = "DATE_IN_FUTURE"
        const val DATE_IN_PAST = "DATE_IN_PAST"

        const val USERNAME_ALREADY_TAKEN = "USERNAME_ALREADY_TAKEN"
        const val EMAIL_ADDRESS_ALREADY_TAKEN = "EMAIL_ADDRESS_ALREADY_TAKEN"
        const val PASSWORD_CONFIRMATION_INCORRECT = "PASSWORD_CONFIRMATION_INCORRECT"
        const val PASSWORD_INCORRECT = "PASSWORD_INCORRECT"
    }

    @Serializable
    data class General(
        @SerialName("status_code")
        val statusCode: Int,

        @SerialName("code")
        val errorCode: String
    )

}