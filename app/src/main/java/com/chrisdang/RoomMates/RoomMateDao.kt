package com.chrisdang.RoomMates

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomMateDao {
    @Query("SELECT * FROM RoomMate_table ORDER BY RoomMateID ASC")
    fun getAlphabetizedRoomMates(): Flow<List<RoomMates>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(roomMates: RoomMates)

    @Query("DELETE FROM RoomMate_table")
    suspend fun deleteAll()
}