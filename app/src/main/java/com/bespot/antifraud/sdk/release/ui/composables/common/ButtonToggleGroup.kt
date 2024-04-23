package com.bespot.antifraud.sdk.release.ui.composables.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bespot.antifraud.sdk.release.ui.theme.Dimens
import com.bespot.antifraud.sdk.release.ui.theme.primaryColor
import com.bespot.antifraud.sdk.release.ui.theme.primaryColorDark

@Composable
fun ButtonToggleGroup(options: List<String>, onClick: (Int) -> Unit) {
    var selectedOption by remember {
        mutableStateOf("")
    }
    val onSelectionChange = { text: String ->
        if (text == selectedOption) {
            selectedOption = ""
        } else {
            selectedOption = text
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        options.forEachIndexed { index, text ->
            Row {
                Button(
                    onClick = {
                        onSelectionChange(text)
                        onClick(index + 1)
                    },
                    modifier = Modifier.padding(horizontal = Dimens.Padding.smallPadding),
                    colors = ButtonColors(
                        containerColor = getContainerColor(selectedOption, text),
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White
                    )
                ) {
                    Text(text = text)
                }
            }
        }
    }
}

private fun getContainerColor(selectedOption: String, text: String): Color {
    return if (selectedOption == text) {
        primaryColorDark
    } else {
        primaryColor
    }
}