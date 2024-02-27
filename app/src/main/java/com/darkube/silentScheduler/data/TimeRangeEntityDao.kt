package com.darkube.silentScheduler.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeRangeEntityDao {
    @Insert
    suspend fun addTimeRangeEntity(timeRange: TimeRangeEntity)

    @Delete
    suspend fun deleteTimeRangeEntity(timeRange: TimeRangeEntity)
    @Query("SELECT * FROM TimeRangeEntity")
    suspend fun getTimeRangeEntities():Flow<List<TimeRangeEntity>>
}