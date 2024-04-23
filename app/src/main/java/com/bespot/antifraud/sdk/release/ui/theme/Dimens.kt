package com.bespot.antifraud.sdk.release.ui.theme

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class Dimens {
    object Padding {
        val smallPadding = 8.dp
        val defaultPadding = 16.dp
        val largePadding = 32.dp
    }

    object TextSize {
        val largeSize = 24.sp
    }
}