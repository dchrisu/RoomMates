package com.chrisdang.RoomMates

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="RoomMate_table")
data class RoomMates(
    @PrimaryKey(autoGenerate = true) val RoomMateID:Int,
    @ColumnInfo(name="FName") val FName: String?,
    @ColumnInfo(name="LName") val LName: String?,
    @ColumnInfo(name="WholeName") val WholeName: String?
)