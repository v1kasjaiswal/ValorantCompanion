package com.stealthx.valorantcompanion.utils

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.stealthx.valorantcompanion.ui.theme.ValoBlack

@Composable
fun SystemBarConfig(){
    var context = LocalContext.current as ComponentActivity

    var navigationColor = if (isSystemInDarkTheme()) {
        SystemBarStyle.dark(
            ValoBlack.toArgb()
        )
    } else {
        SystemBarStyle.light(
            ValoBlack.toArgb(),
            ValoBlack.toArgb()
        )
    }

    var statusBarColor = if (isSystemInDarkTheme()) {
        SystemBarStyle.dark(
            ValoBlack.toArgb()
        )
    } else {
        SystemBarStyle.light(
            ValoBlack.toArgb(),
            ValoBlack.toArgb()
        )
    }

    context.enableEdgeToEdge(
        statusBarStyle = statusBarColor,
        navigationBarStyle = navigationColor
    )
}
