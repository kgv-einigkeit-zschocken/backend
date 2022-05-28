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
fun Application.getEnvironmentVariable(key: String): String {
    return environment.config.propertyOrNull(key)?.getString() ?:
    throw InitializationException("Environment variable for key $key not found.")
}