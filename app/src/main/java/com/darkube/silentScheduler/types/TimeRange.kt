package com.darkube.silentScheduler.types


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

data class Time24(
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
    fun get12Hours(): Time {
        val period = (if (hours >= 12) TimePeriod.PM else TimePeriod.AM)
        var hours12 = hours - (if (hours > 12) 12 else 0)
        if (hours12 == 0) {
            hours12 = 12
        }
        return Time(hours = hours12, minutes = minutes, period = period)
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
    fun isValid(): Boolean {
        return duration() > 0
    }
}
