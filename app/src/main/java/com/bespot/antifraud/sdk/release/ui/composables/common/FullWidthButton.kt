package com.bespot.antifraud.sdk.release.ui.composables.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bespot.antifraud.sdk.release.ui.theme.Dimens
import com.bespot.antifraud.sdk.release.ui.theme.Theme

@Composable
fun FullWidthButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.Padding.smallPadding),
        colors = Theme.defaultButtonColors,
        onClick = onClick
    ) {
        Text(text = text, maxLines = 1)
    }
}