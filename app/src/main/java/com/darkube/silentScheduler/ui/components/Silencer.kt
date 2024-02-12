package com.darkube.silentScheduler.ui.components

import android.content.Context
import android.media.AudioManager
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun Silence(modifier:Modifier = Modifier){
    val context = LocalContext.current
    val audioManager = remember{context.getSystemService(Context.AUDIO_SERVICE) as AudioManager}

    val currentRingMode = audioManager.ringerMode

    /*
    audioManger.ringerMode returns three values
    0 - If phone is in silent mode (can also be called as DND- do not disturb)
    1 - If phone is in vibration mode
    2 - If phone is in normal mode
     */

    Log.d("Device Mode", "${audioManager.ringerMode}")
}