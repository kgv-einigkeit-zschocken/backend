package de.kgveinigkeitzschocken.core.manager

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class LocalDateManager {

    fun getLocalDate(timestamp: Long): LocalDate {
        return LocalDate.ofInstant(
            Instant.ofEpochSecond(timestamp),
            ZoneId.systemDefault()
        )
    }

    fun getLocalDate(year: Int, month: Int, dayOfMonth: Int): LocalDate {
        return LocalDate.of(year, month, dayOfMonth)
    }

}