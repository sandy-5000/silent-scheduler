package com.darkube.silentScheduler.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.darkube.silentScheduler.viewmodels.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun RingerMonitor(context: Context, viewModel: MainViewModel) {
    LaunchedEffect(key1 = Unit) {
        while (true) {
            getSoundMode(context = context, viewModel = viewModel)
            delay(1000)
        }
    }
}