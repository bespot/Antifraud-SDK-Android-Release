package com.bespot.antifraud.sdk.release.ui.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bespot.shared.common.models.Action
import kotlin.random.Random

fun Action?.toTitle(): String {
    return when (this) {
        Action.SAFE -> "✅ Device is safe"
        Action.MONITOR -> "Device should be monitored"
        Action.LIMIT_ACCESS -> "⚠️ Device should have limited access"
        Action.BLOCK -> "⛔ Device should be blocked"
        Action.NOT_SAFE -> "📵 Device is not safe"
        null -> "Select a fraud detection method"
    }
}

private fun Activity.requestPermissions(permissions: Array<String>) {
    ActivityCompat.requestPermissions(
        this,
        permissions,
        Random.nextInt(100, 999)
    )
}

fun Activity.checkForLocationPermission() {
    if (ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        Toast.makeText(this, "Location permission is granted", Toast.LENGTH_SHORT).show()
    } else {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
    }
}

fun Activity.checkForReadStoragePermission() {
    val permissionRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_AUDIO
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }
    if (ActivityCompat.checkSelfPermission(
            this,
            permissionRequest,
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        requestPermissions(arrayOf(permissionRequest))
    } else {
        Toast.makeText(this, "Read Storage permission is granted", Toast.LENGTH_SHORT).show()
    }
}

fun Activity.checkForWriteStoragePermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Toast.makeText(
            this,
            "In Android 13+ you don't need Write Storage permission",
            Toast.LENGTH_SHORT
        ).show()
    } else {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(
                this,
                "Write Storage permission is granted",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
        }
    }
}
