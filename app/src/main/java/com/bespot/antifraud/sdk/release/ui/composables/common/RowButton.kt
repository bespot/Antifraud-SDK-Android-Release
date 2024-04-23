package com.bespot.antifraud.sdk.release.ui.composables.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bespot.antifraud.sdk.release.ui.theme.Dimens
import com.bespot.antifraud.sdk.release.ui.theme.Theme

@Composable
fun RowScope.RowButton(text: String, enabled: Boolean = true, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(horizontal = Dimens.Padding.smallPadding)
            .weight(1f),
        enabled = enabled,
        colors = Theme.defaultButtonColors,
        onClick = onClick
    ) {
        Text(text = text, maxLines = 1)
    }
}
