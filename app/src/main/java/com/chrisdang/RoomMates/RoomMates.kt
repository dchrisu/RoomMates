package com.chrisdang.RoomMates

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="roomMate_table")
data class RoomMates(@PrimaryKey(autoGenerate = true) val id:Int, @ColumnInfo(name="roomMate") val roomMate: String)