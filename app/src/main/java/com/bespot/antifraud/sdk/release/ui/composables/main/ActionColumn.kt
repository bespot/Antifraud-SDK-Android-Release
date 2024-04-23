package com.bespot.antifraud.sdk.release.ui.composables.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.bespot.antifraud.sdk.release.ui.CheckResult
import com.bespot.antifraud.sdk.release.ui.MainActivityViewModel
import com.bespot.antifraud.sdk.release.ui.composables.common.FullWidthButton
import com.bespot.antifraud.sdk.release.ui.composables.common.RowButton
import com.bespot.antifraud.sdk.release.ui.theme.Dimens
import com.bespot.antifraud.sdk.release.ui.utils.toTitle

@Composable
fun ActionColumn(viewModel: MainActivityViewModel) {
    val checkResult: CheckResult by remember {
        viewModel.checkResult
    }
    val isSubscribed: Boolean by remember {
        viewModel.isSubsribed
    }
    Column {
        Text(
            text = checkResult.action.toTitle(),
            modifier = Modifier
                .padding(vertical = Dimens.Padding.defaultPadding)
                .fillMaxWidth(),
            fontSize = Dimens.TextSize.largeSize,
            textAlign = TextAlign.Center
        )
        Row {
            RowButton(text = "SUBSCRIBE", enabled = !isSubscribed) {
                viewModel.subscribe()
            }
            RowButton(text = "UNSUBSCRIBE", enabled = isSubscribed) {
                viewModel.unsubscribe()
            }
        }
        FullWidthButton(text = "CHECK NOW") {
            viewModel.check()
        }
    }
}