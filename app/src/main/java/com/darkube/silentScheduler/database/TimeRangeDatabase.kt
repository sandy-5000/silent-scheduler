package com.darkube.silentScheduler.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.darkube.silentScheduler.data.TimeRangeEntity
import com.darkube.silentScheduler.data.TimeRangeEntityDao

@Database(
    entities = [TimeRangeEntity::class],
    version = 1
)
abstract class TimeRangeDatabase:RoomDatabase() {
    abstract val dao: TimeRangeEntityDao
}