package com.stealthx.valorantcompanion.utils

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.stealthx.valorantcompanion.ui.theme.ValoBlack

val VALORANT_API_BASE_URL = "https://valorant-api.com/v1/"

val ONBOARD_KEY = booleanPreferencesKey("onBoardPref")