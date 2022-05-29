package de.kgveinigkeitzschocken.core.manager

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset

class DateManager {

    /**
     * Converts a timestamp to a [LocalDate]
     *
     * @param timestamp The timestamp to convert (milliseconds since 1970-01-01T00:00) - [Long]
     * @return A [LocalDate] representation of the timestamp
     */
    fun getLocalDate(timestamp: Long): LocalDate {
        return LocalDate.ofInstant(
            Instant.ofEpochSecond(timestamp),
            ZoneId.systemDefault()
        )
    }

    /**
     * Creates a [LocalDate] instance with the passed parameters
     *
     * @param year The year of the [LocalDate] - [Int]
     * @param month The month of the [LocalDate] (1-12) - [Int]
     * @param dayOfMonth The day of month of the [LocalDate] (1-31) - [Int]
     * @return A [LocalDate] representation of the passed arguments
     */
    fun getLocalDate(year: Int, month: Int, dayOfMonth: Int): LocalDate {
        return LocalDate.of(year, month, dayOfMonth)
    }

    fun getTimestamp(date: LocalDate): Long {
        return date.atStartOfDay().toEpochSecond(ZoneOffset.UTC)
    }

}