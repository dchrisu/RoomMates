package com.chrisdang.RoomMates
import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RoomMateApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { RoomMateDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { RoomMateRepository(database.roomMateDao()) }
}