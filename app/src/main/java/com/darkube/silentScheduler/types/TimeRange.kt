package com.darkube.silentScheduler.types

import androidx.compose.ui.unit.dp

enum class TimePeriod(val value: String) {
    AM("am"),
    PM("pm")
}

data class Time(
    val hours: Int,
    val minutes: Int,
    val period: TimePeriod,
) {
    override fun toString(): String {
        val formattedHours = String.format("%02d", hours)
        val formattedMinutes = String.format("%02d", minutes)
        return "${formattedHours}:${formattedMinutes}${period.value}"
    }
}

data class TimeRange(
    val start: Time,
    val end: Time,
) {
    override fun toString(): String {
        return "${start.toString()} - ${end.toString()}"
    }
    private fun duration(): Int {
        val startHours = start.hours + (if (start.period == TimePeriod.PM) 12 else 0)
        val endHours = end.hours + (if (end.period == TimePeriod.PM) 12 else 0)
        return (endHours - startHours) * 60 + (end.minutes - start.minutes)
    }

    fun durationToString(): String {
        val difference = duration()
        if (difference <= 0) {
            return "InValid Range"
        }
        val hours = difference / 60
        val mints = difference % 60
        if (hours == 0) {
            return "${mints}m"
        }
        return "${hours}h${mints}m"
    }
}

