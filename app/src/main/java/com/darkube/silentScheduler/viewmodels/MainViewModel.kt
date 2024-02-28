package com.darkube.silentScheduler.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkube.silentScheduler.data.TimeRangeEntity
import com.darkube.silentScheduler.data.TimeRangeEntityDao
import com.darkube.silentScheduler.types.AudioMode
import com.darkube.silentScheduler.types.Time
import com.darkube.silentScheduler.types.TimeRange
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    var openDialog by mutableStateOf(false)
        private set
    var currentMode by mutableStateOf(AudioMode.SILENT)
        private set

    private val _schedules = mutableStateListOf(
        TimeRange(
            start = Time(hours = 9, minutes = 0),
            end = Time(hours = 9, minutes = 30),
        ),
        TimeRange(
            start = Time(hours = 10, minutes = 0),
            end = Time(hours = 10, minutes = 30),
        ),
        TimeRange(
            start = Time(hours = 11, minutes = 0),
            end = Time(hours = 11, minutes = 45),
        ),
        TimeRange(
            start = Time(hours = 12, minutes = 0),
            end = Time(hours = 12, minutes = 40),
        ),
        TimeRange(
            start = Time(hours = 14, minutes = 0),
            end = Time(hours = 15, minutes = 35),
        ),
        TimeRange(
            start = Time(hours = 16, minutes = 10),
            end = Time(hours = 16, minutes = 35),
        ),
        TimeRange(
            start = Time(hours = 17, minutes = 15),
            end = Time(hours = 18, minutes = 0),
        ),
    )

    val schedules: State<List<TimeRange>> = derivedStateOf {
        _schedules.toList()
    }

    fun addNewSchedule(timeRange: TimeRange): Boolean {
        val overlappingSchedules = _schedules.filter { it.overLaps(timeRange) }
        if (overlappingSchedules.isEmpty()) {
            val index =
                _schedules.indexOfFirst { it.start.timeStamp() > timeRange.start.timeStamp() }
            if (index == -1) {
                _schedules.add(timeRange)
            } else {
                _schedules.add(index, timeRange)
            }
            return true
        }
        return false
    }

    fun removeSchedule(index: Int): Boolean {
        Log.d("delete-1", "$index")
        if (index in 0 until _schedules.size) {
            Log.d("delete-2", "$index")
            _schedules.removeAt(index)
            return true
        }
        return false
    }

    private fun serializeSchedules(): String {
        return _schedules.joinToString(separator = ",") { it.serialize() }
    }
    private fun deSerializeSchedules(data: String): SnapshotStateList<TimeRange> {
        val schedules = data.split(",").map {
            TimeRange.deSerialize(it)
        }.toMutableStateList()
        return schedules
    }

    fun openDialogStatus() {
        openDialog = true
    }

    fun closeDialogStatus() {
        openDialog = false
    }

    fun setToNormalMode() {
        currentMode = AudioMode.NORMAL
    }

    fun setToSilentMode() {
        currentMode = AudioMode.SILENT
    }


}

