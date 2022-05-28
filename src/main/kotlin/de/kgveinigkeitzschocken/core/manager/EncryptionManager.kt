package de.kgveinigkeitzschocken.core.manager

import at.favre.lib.crypto.bcrypt.BCrypt

class EncryptionManager {

    companion object {
        private const val COST = 12
    }

    fun encrypt(cleartext: String): String {
        return BCrypt.withDefaults().hashToString(COST, cleartext.toCharArray())
    }

}