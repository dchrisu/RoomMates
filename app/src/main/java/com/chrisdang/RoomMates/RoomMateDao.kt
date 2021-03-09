package com.chrisdang.RoomMates

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomMateDao{
    @Query("SELECT * FROM roomMate_table ORDER BY roomMate DESC")
    fun getDescRoomMates(): Flow<List<RoomMates>>
}