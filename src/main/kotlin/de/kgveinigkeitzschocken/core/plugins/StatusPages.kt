package de.kgveinigkeitzschocken.core.plugins

import de.kgveinigkeitzschocken.core.exceptions.DateInFutureException
import de.kgveinigkeitzschocken.core.exceptions.DateInPastException
import de.kgveinigkeitzschocken.core.exceptions.EmailAddressAlreadyTakenException
import de.kgveinigkeitzschocken.core.exceptions.EntityNotFoundException
import de.kgveinigkeitzschocken.core.exceptions.FieldAbsentException
import de.kgveinigkeitzschocken.core.exceptions.PasswordConfirmationIncorrectException
import de.kgveinigkeitzschocken.core.exceptions.PasswordIncorrectException
import de.kgveinigkeitzschocken.core.exceptions.UsernameAlreadyTakenException
import de.kgveinigkeitzschocken.core.model.ApiError
import de.kgveinigkeitzschocken.core.util.error
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.ContentTransformationException
import kotlinx.serialization.SerializationException

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception(ContentTransformationException::class) { call, _ ->
            call.error(ApiError.ErrorCode.SERIALIZATION_FAILED)
        }

        exception(SerializationException::class) { call, _ ->
            call.error(ApiError.ErrorCode.SERIALIZATION_FAILED)
        }

        exception(EntityNotFoundException::class) { call, _ ->
            call.error(ApiError.ErrorCode.ENTITY_NOT_FOUND, HttpStatusCode.NotFound)
        }

        exception(FieldAbsentException::class) { call, _ ->
            call.error(ApiError.ErrorCode.FIELD_ABSENT)
        }

        exception(PasswordIncorrectException::class) { call, _ ->
            call.error(ApiError.ErrorCode.PASSWORD_INCORRECT)
        }

        exception(PasswordConfirmationIncorrectException::class) { call, _ ->
            call.error(ApiError.ErrorCode.PASSWORD_CONFIRMATION_INCORRECT)
        }

        exception(UsernameAlreadyTakenException::class) { call, _ ->
            call.error(ApiError.ErrorCode.USERNAME_ALREADY_TAKEN)
        }

        exception(EmailAddressAlreadyTakenException::class) { call, _ ->
            call.error(ApiError.ErrorCode.EMAIL_ADDRESS_ALREADY_TAKEN)
        }

        exception(DateInFutureException::class) { call, _ ->
            call.error(ApiError.ErrorCode.DATE_IN_FUTURE)
        }

        exception(DateInPastException::class) { call, _ ->
            call.error(ApiError.ErrorCode.DATE_IN_PAST)
        }

    }
}