package de.kgveinigkeitzschocken.core.inject

import de.kgveinigkeitzschocken.core.manager.EncryptionManager
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val di = DI {
    bindSingleton { EncryptionManager() }
}