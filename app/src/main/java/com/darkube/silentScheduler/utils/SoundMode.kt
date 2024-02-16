package com.darkube.silentScheduler.utils

import android.content.Context
import android.media.AudioManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.darkube.silentScheduler.viewmodels.MainViewModel

@Composable
fun GetSoundMode(
    viewModel: MainViewModel,
) {
    /**
     * audioManger.ringerMode returns three values
     * 0 - If phone is in silent mode (can also be called as DND- do not disturb)
     * 1 - If phone is in vibration mode
     * 2 - If phone is in normal mode
     */
    val context = LocalContext.current
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
}
