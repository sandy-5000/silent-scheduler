package com.darkube.silentScheduler.ui.components

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun GetPermissions() {
    val audioSettingPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            Log.d("permission", "$isGranted")
        }
    )
    audioSettingPermissionLauncher.launch(
        Manifest.permission.RECORD_AUDIO
    )
}