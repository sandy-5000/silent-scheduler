package com.darkube.silentScheduler.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    var openDialog by mutableStateOf(false)
    private set
    fun openDialogStatus() {
        openDialog = true
    }
    fun closeDialogStatus() {
        openDialog = false
    }
}