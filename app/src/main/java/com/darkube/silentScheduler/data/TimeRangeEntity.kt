package com.darkube.silentScheduler.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.darkube.silentScheduler.types.Time

@Entity
data class TimeRangeEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val start:Time,
    val end:Time,
)
