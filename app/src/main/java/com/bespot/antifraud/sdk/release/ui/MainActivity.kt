package com.bespot.antifraud.sdk.release.ui

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bespot.antifraud.sdk.release.ui.composables.common.AntifraudTopAppBar
import com.bespot.antifraud.sdk.release.ui.composables.common.TicketProvider
import com.bespot.antifraud.sdk.release.ui.composables.main.ActionColumn
import com.bespot.antifraud.sdk.release.ui.composables.main.PermissionColumn
import com.bespot.antifraud.sdk.release.ui.composables.main.TimestampRow
import com.bespot.antifraud.sdk.release.ui.composables.main.UserIdColumn
import com.bespot.antifraud.sdk.release.ui.theme.AntifraudSDKAndroidReleaseTheme
import com.bespot.antifraud.sdk.release.ui.theme.Dimens

class MainActivity : ComponentActivity() {
    companion object {
        const val SAVED_USER_ID = "saved_user_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = getPreferences(MODE_PRIVATE)
        setContent {
            AntifraudSDKAndroidReleaseTheme {
                MainScreenUI(this, preferences)
            }
        }
    }
}

@Composable
fun MainScreenUI(activity: Activity, preferences: SharedPreferences) {
    val viewModel = MainActivityViewModel()
    Scaffold(
        topBar = { AntifraudTopAppBar {
            TicketProvider(
                context = activity.applicationContext,
                viewModel = viewModel
            )
        }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = Dimens.Padding.smallPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ActionColumn(viewModel)
            PermissionColumn(activity)
            UserIdColumn(viewModel, preferences)
            TimestampRow(viewModel)
        }
    }
}
