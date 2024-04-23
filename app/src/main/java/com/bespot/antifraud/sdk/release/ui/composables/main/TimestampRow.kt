package com.bespot.antifraud.sdk.release.ui.composables.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bespot.antifraud.sdk.release.ui.MainActivityViewModel

@Composable
fun TimestampRow(viewModel: MainActivityViewModel) {
    val timestamp = remember {
        viewModel.timestamp
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        if (viewModel.timestamp.value.isEmpty()) {
            Text("Waiting for updates...")
        } else {
            Text("Last Update: ${timestamp.value}")
        }
    }
}