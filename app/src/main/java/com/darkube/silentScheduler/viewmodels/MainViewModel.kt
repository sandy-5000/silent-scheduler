package com.darkube.silentScheduler.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkube.silentScheduler.types.AudioMode


class MainViewModel: ViewModel() {
    var openDialog by mutableStateOf(false)
    private set

    var currentMode by mutableStateOf(AudioMode.SILENT)
    private set
    var currentTime = MutableLiveData(System.currentTimeMillis())
    private set
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
