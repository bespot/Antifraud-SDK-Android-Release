package com.bespot.antifraud.sdk.release.ui.composables.main

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.bespot.antifraud.sdk.release.ui.MainActivity
import com.bespot.antifraud.sdk.release.ui.MainActivityViewModel
import com.bespot.antifraud.sdk.release.ui.composables.common.ButtonToggleGroup
import com.bespot.antifraud.sdk.release.ui.theme.Dimens

@Composable
fun UserIdColumn(viewModel: MainActivityViewModel, preferences: SharedPreferences) {
    var selectedIndex: Int? by remember {
        mutableStateOf(preferences.getIntOrNull(MainActivity.SAVED_USER_ID))
    }
    val options = listOf("UserId 1", "UserId 2", "UserId 3")
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Current Id: ${selectedIndex?.let { "user_id_$it" } ?: "None"}",
            fontSize = Dimens.TextSize.largeSize,
            maxLines = 1
        )
        ButtonToggleGroup(options = options, selectedIndex = selectedIndex) { index ->
            selectedIndex = if (index == selectedIndex) {
                null
            } else {
                index
            }
            updateUserId(selectedIndex, viewModel, preferences)
        }
    }
}

private fun SharedPreferences.getIntOrNull(key: String): Int? {
    val value = this.getInt(key, -1)
    return if (value == -1) null else value
}

private fun updateUserId(
    newUserId: Int?,
    viewModel: MainActivityViewModel,
    preferences: SharedPreferences
) {
    viewModel.setUserId(newUserId?.let { "user_id_$it" } ?: "")
    preferences.edit()
        .putInt(MainActivity.SAVED_USER_ID, newUserId ?: -1)
        .apply()
}