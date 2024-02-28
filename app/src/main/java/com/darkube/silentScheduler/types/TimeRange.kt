package com.darkube.silentScheduler.types


enum class TimePeriod(val value: String) {
    AM("am"), PM("pm")
}

enum class AudioMode() {
    SILENT, NORMAL
}

data class Time12(
    val hours: Int,
    val minutes: Int,
    val period: TimePeriod,
) {
    override fun toString(): String {
        val formattedHours = String.format("%02d", hours)
        val formattedMinutes = String.format("%02d", minutes)
        return "${formattedHours}:${formattedMinutes}${period.value}"
    }

    fun get24Hours(): Time {
        var hours24 = hours
        if (period == TimePeriod.PM) {
            if (hours24 != 12) {
                hours24 += 12
            }
        } else {
            if (hours24 == 12) {
                hours24 = 0
            }
        }
        return Time(hours = hours24, minutes = minutes)
    }
}

data class Time(
    val hours: Int,
    val minutes: Int,
) {
    override fun toString(): String {
        val period = (if (hours >= 12) TimePeriod.PM else TimePeriod.AM)
        var hours12 = hours - (if (hours > 12) 12 else 0)
        if (hours12 == 0) {
            hours12 = 12
        }
        val formattedHours = String.format("%02d", hours12)
        val formattedMinutes = String.format("%02d", minutes)
        return "${formattedHours}:${formattedMinutes}${period.value}"
    }

    fun timeStamp(): Long {
        return (60 * hours + minutes).toLong() * 60 * 1000
    }

    fun serialize(): String {
        return "$hours|$minutes"
    }
    companion object {
        fun deSerialize(data: String): Time {
            val (hours, minutes) = data.split('|').let { (hours, minutes) -> Pair(hours.toInt(), minutes.toInt()) }
            return Time(hours = hours, minutes = minutes)
        }
    }

    fun get12Hours(): Time12 {
        val period = (if (hours >= 12) TimePeriod.PM else TimePeriod.AM)
        var hours12 = hours - (if (hours > 12) 12 else 0)
        if (hours12 == 0) {
            hours12 = 12
        }
        return Time12(hours = hours12, minutes = minutes, period = period)
    }
}

data class TimeRange(
    var start: Time,
    var end: Time,
) {
    override fun toString(): String {
        return "$start - $end"
    }

    private fun duration(): Int {
        return (end.hours - start.hours) * 60 + (end.minutes - start.minutes)
    }

    fun overLaps(range: TimeRange): Boolean {
        return (start.timeStamp() <= range.start.timeStamp() && range.start.timeStamp() <= end.timeStamp()) || (start.timeStamp() <= range.end.timeStamp() && range.end.timeStamp() <= end.timeStamp())
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

    fun serialize(): String {
        return "${start.serialize()}#${end.serialize()}"
    }
    companion object {
        fun deSerialize(data: String): TimeRange {
            val (start, end) = data.split('#').let { (start, end) -> Pair(Time.deSerialize(start), Time.deSerialize(end)) }
            return TimeRange(start = start, end = end)
        }
    }
    fun isValid(): Boolean {
        return duration() > 0
    }
}
