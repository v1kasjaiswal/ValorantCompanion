package com.stealthx.valorantcompanion.presentation.common

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.stealthx.valorantcompanion.ui.theme.ValoBlack
import com.stealthx.valorantcompanion.ui.theme.ValoRed
import com.stealthx.valorantcompanion.ui.theme.ValoWhite

@Composable
fun SetupSystemBar(keepStatusBarTransparent: Boolean = true) {

    val context = LocalContext.current as ComponentActivity

    val systemBarColor = if (isSystemInDarkTheme()) SystemBarStyle.dark(ValoBlack.toArgb()) else SystemBarStyle.light(ValoWhite.toArgb(), ValoWhite.toArgb())

    context.enableEdgeToEdge(
        statusBarStyle = if (keepStatusBarTransparent) SystemBarStyle.auto(Color.Transparent.toArgb(), Color.Transparent.toArgb()) else systemBarColor,
        navigationBarStyle = systemBarColor
    )
}