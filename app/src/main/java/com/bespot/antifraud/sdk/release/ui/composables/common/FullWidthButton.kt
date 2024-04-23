package com.bespot.antifraud.sdk.release.ui.composables.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bespot.antifraud.sdk.release.ui.theme.Dimens
import com.bespot.antifraud.sdk.release.ui.theme.primaryColor

@Composable
fun FullWidthButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Padding.smallPadding),
        colors = ButtonColors(primaryColor, Color.White, Color.Gray, Color.White),
        onClick = onClick
    ) {
        Text(text = text, maxLines = 1)
    }
}