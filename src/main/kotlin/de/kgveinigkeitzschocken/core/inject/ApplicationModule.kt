package de.kgveinigkeitzschocken.core.inject

import de.kgveinigkeitzschocken.core.manager.EncryptionManager
import de.kgveinigkeitzschocken.core.manager.DateManager
import de.kgveinigkeitzschocken.db.service.UserService
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val di = DI {
    bindSingleton { EncryptionManager() }
    bindSingleton { DateManager() }
    bindSingleton { UserService() }
}