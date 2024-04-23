package com.bespot.antifraud.sdk.release.ui.composables.main

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.bespot.antifraud.sdk.release.ui.composables.common.FullWidthButton
import com.bespot.antifraud.sdk.release.ui.utils.checkForLocationPermission
import com.bespot.antifraud.sdk.release.ui.utils.checkForReadStoragePermission
import com.bespot.antifraud.sdk.release.ui.utils.checkForWriteStoragePermission

@Composable
fun PermissionColumn(activity: Activity) {
    Column {
        FullWidthButton(text = "REQUEST LOCATION PERMISSION") { activity.checkForLocationPermission() }
        FullWidthButton(text = "REQUEST READ EXTERNAL PERMISSION") { activity.checkForReadStoragePermission() }
        FullWidthButton(text = "REQUEST WRITE EXTERNAL PERMISSION") { activity.checkForWriteStoragePermission() }
    }
}