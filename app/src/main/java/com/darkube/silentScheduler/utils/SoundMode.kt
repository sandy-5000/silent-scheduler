package com.darkube.silentScheduler.utils

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.provider.Settings
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.darkube.silentScheduler.viewmodels.MainViewModel

/**
 * audioManger.ringerMode returns three values
 * 0 - If phone is in silent mode (can also be called as DND- do not disturb)
 * 1 - If phone is in vibration mode
 * 2 - If phone is in normal mode
 */

fun getSoundMode(
    context: Context,
    viewModel: MainViewModel,
): Int {
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    when (audioManager.ringerMode) {
        AudioManager.RINGER_MODE_SILENT -> {
            viewModel.setToSilentMode()
        }
        AudioManager.RINGER_MODE_NORMAL -> {
            viewModel.setToNormalMode()
        }
        AudioManager.RINGER_MODE_VIBRATE -> {
            viewModel.setToSilentMode()
        }
        else -> {
            viewModel.setToSilentMode()
        }
    }
    return audioManager.ringerMode
}


fun setSoundMode(
    context: Context,
    viewModel: MainViewModel,
    audioMode: Int,
) {
    val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    if (!notificationManager.isNotificationPolicyAccessGranted) {
        val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
        startActivity(context, intent, null)
    } else {
        when (audioMode) {
            AudioManager.RINGER_MODE_SILENT -> {
                audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
                viewModel.setToSilentMode()
            }
            AudioManager.RINGER_MODE_NORMAL -> {
                audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
                viewModel.setToNormalMode()
            }
            AudioManager.RINGER_MODE_VIBRATE -> {
                audioManager.ringerMode = AudioManager.RINGER_MODE_VIBRATE
                viewModel.setToSilentMode()
            }
            else -> {
                viewModel.setToSilentMode()
            }
        }
    }
}