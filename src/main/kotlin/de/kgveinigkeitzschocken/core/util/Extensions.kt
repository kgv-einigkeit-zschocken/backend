package de.kgveinigkeitzschocken.core.util

import de.kgveinigkeitzschocken.core.exceptions.InitializationException
import io.ktor.server.application.Application

/**
 * Returns a environment variable for a specific key
 *
 * @param key The key of the environment variable - [String]
 * @return The environment variable for the key - [String]
 *
 * @throws [InitializationException] if no environment variable has been found for the key
 */
@Throws(InitializationException::class)
fun Application.getStringEnv(key: String): String {
    return environment.config.propertyOrNull(key)?.getString() ?:
    throw InitializationException("Environment variable for key $key not found.")
}

/**
 * Returns a environment variable for a specific key
 *
 * @param key The key of the environment variable - [String]
 * @return The environment variable for the key - [Int]
 *
 * @throws [InitializationException] if no environment variable has been found for the key
 * or the environment variable isn't a valid integer
 */
@Throws(InitializationException::class)
fun Application.getIntEnv(key: String): Int {
    try {
        return environment.config.propertyOrNull(key)?.getString()?.toInt() ?:
        throw InitializationException("Environment variable for key $key not found.")
    } catch (e: java.lang.NumberFormatException) {
        throw InitializationException("Environment variable for key $key is invalid: Expected int.")
    }
}