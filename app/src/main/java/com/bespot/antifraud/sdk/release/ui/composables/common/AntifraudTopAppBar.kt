package com.bespot.antifraud.sdk.release.ui.composables.common

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import com.bespot.antifraud.sdk.release.ui.CheckResult
import com.bespot.antifraud.sdk.release.ui.MainActivityViewModel
import com.bespot.antifraud.sdk.release.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AntifraudTopAppBar(action: (@Composable () -> Unit)? = null) {
    TopAppBar(
        title = { Text("Antifraud SDK Sample App") },
        colors = TopAppBarColors(primaryColor, Color.White, Color.White, Color.White, Color.White),
        actions = { action?.invoke() }
    )
}

@Composable
fun TicketProvider(context: Context, viewModel: MainActivityViewModel) {
    val clipboard = LocalClipboardManager.current
    val checkResult: CheckResult by remember {
        viewModel.checkResult
    }
    Icon(
        imageVector = Icons.Outlined.ConfirmationNumber,
        contentDescription = "Antifraud Ticket Id",
        modifier = Modifier.clickable {
            clipboard.setText(AnnotatedString(checkResult.ticket))
            Toast.makeText(context, "Ticket copied to clipboard", Toast.LENGTH_SHORT).show()
        }
    )
}