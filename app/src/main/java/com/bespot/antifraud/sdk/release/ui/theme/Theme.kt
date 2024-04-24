package com.bespot.antifraud.sdk.release.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = primaryColor,
    secondary = primaryColorDark
)

private val LightColorScheme = lightColorScheme(
    primary = primaryColor,
    secondary = primaryColorDark
)

object Theme {
    val defaultButtonColors = ButtonColors(
        containerColor = primaryColorPastel,
        contentColor = primaryColor,
        disabledContainerColor = primaryColor,
        disabledContentColor = Color.White
    )

    fun toggleButtonColors(selectedOption: String, text: String) =
        if (selectedOption == text) {
            ButtonColors(
                containerColor = primaryColor,
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            )
        } else {
            ButtonColors(
                containerColor = primaryColorPastel,
                contentColor = primaryColor,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            )
        }
}

@Composable
fun AntifraudSDKAndroidReleaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = primaryColorDark.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}