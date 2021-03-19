package com.chrisdang.RoomMates

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(RoomMates::class), version = 1, exportSchema = false)
public abstract class RoomMateDatabase : RoomDatabase() {

    abstract fun roomMateDao(): RoomMateDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RoomMateDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
            ): RoomMateDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomMateDatabase::class.java,
                    "RoomMate_table"
                )
                    .addCallback(RoomMateDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class RoomMateDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        var roomMateDao = database.roomMateDao()

                        // Delete all content here.
                        roomMateDao.deleteAll()

                        // Populate with dummy data
                        populateDatabase(database.roomMateDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(wordDao: RoomMateDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            wordDao.deleteAll()

            var roomMate = RoomMates(1,"Chris", "Dang", "Chris Dang")
            wordDao.insert(roomMate)
            roomMate = RoomMates(2,"Chris", "Dang", "Chris Dang")
            wordDao.insert(roomMate)
        }
    }
}