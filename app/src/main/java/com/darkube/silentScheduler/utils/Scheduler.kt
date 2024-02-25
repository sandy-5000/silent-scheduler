package com.darkube.silentScheduler.utils

import android.app.NotificationManager
import android.content.Context
import android.media.AudioManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.darkube.silentScheduler.types.TimeRange
import java.util.Calendar
import java.util.concurrent.TimeUnit

class OnScheduler(
    context: Context,
    workerParams: WorkerParameters,
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val context = applicationContext
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (notificationManager.isNotificationPolicyAccessGranted) {
            audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
        }
        return Result.success()
    }
}

class OffScheduler(
    context: Context,
    workerParams: WorkerParameters,
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val context = applicationContext
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (notificationManager.isNotificationPolicyAccessGranted) {
            audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
        }
        return Result.success()
    }
}

fun scheduleWork(context: Context, timeRange: TimeRange) {
    val currentTime = Calendar.getInstance()
    val now = currentTime.timeInMillis

    val onScheduledTime = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, timeRange.start.hours)
        set(Calendar.MINUTE, timeRange.start.minutes)
        set(Calendar.SECOND, 0)
        if (before(currentTime)) {
            add(Calendar.DAY_OF_MONTH, 1)
        }
    }.timeInMillis
    val onDelay = onScheduledTime - now
    val onWorkRequest = OneTimeWorkRequestBuilder<OnScheduler>()
        .setInitialDelay(onDelay, TimeUnit.MILLISECONDS)
        .build()
    WorkManager.getInstance(context).enqueue(onWorkRequest)

    val offScheduledTime = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, timeRange.start.hours)
        set(Calendar.MINUTE, timeRange.start.minutes)
        set(Calendar.SECOND, 0)
        if (before(currentTime)) {
            add(Calendar.DAY_OF_MONTH, 1)
        }
    }.timeInMillis
    val offDelay = offScheduledTime - now
    val offWorkRequest = OneTimeWorkRequestBuilder<OffScheduler>()
        .setInitialDelay(offDelay, TimeUnit.MILLISECONDS)
        .build()
    WorkManager.getInstance(context).enqueue(offWorkRequest)
}

fun cancelScheduledWork(context: Context) {
    WorkManager.getInstance(context).cancelAllWork()
}

fun reScheduleWorks(context: Context, ranges: List<TimeRange>) {
    cancelScheduledWork(context = context)
    for (timeRange in ranges) {
        scheduleWork(context = context, timeRange = timeRange)
    }
}
