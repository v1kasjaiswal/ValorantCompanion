package com.stealthx.valorantcompanion.navigation.bottomnavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stealthx.valorantcompanion.presentation.agentscreen.AgentScreen
import com.stealthx.valorantcompanion.presentation.homescreen.HomeScreen
import com.stealthx.valorantcompanion.presentation.mapsscreen.MapsScreen
import com.stealthx.valorantcompanion.presentation.modesscreen.ModesScreen
import com.stealthx.valorantcompanion.presentation.weaponsscreen.WeaponsScreen

@Composable
fun SetupBottomNavigation(
    appNavHostController: NavHostController,
    navHostController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navHostController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen()
        }

        composable<Agents> {
            AgentScreen(appNavHostController = appNavHostController)
        }

        composable<Weapons> {
            WeaponsScreen(appNavHostController = appNavHostController)
        }

        composable<Maps> {
            MapsScreen()
        }

        composable<Modes> {
            ModesScreen()
        }
    }
}