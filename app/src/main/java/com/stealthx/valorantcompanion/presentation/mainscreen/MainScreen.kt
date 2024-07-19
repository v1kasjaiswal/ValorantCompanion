package com.stealthx.valorantcompanion.presentation.mainscreen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.navigation.bottomnavigation.SetupBottomNavigation
import com.stealthx.valorantcompanion.navigation.bottomnavigation.utils.SBottomNavItems
import com.stealthx.valorantcompanion.presentation.common.SetupSystemBar
import com.stealthx.valorantcompanion.ui.theme.ValoBlack
import com.stealthx.valorantcompanion.ui.theme.ValoGrey
import com.stealthx.valorantcompanion.ui.theme.ValoRed
import com.stealthx.valorantcompanion.ui.theme.ValoWhite

@Composable
fun MainScreen(appNavHostController: NavHostController) {
    SetupSystemBar(keepStatusBarTransparent = false)

    MainScreenDesign(appNavHostController = appNavHostController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenDesign(
    navHostController: NavHostController = rememberNavController(),
    appNavHostController: NavHostController
) {

    Scaffold(
        contentColor = ValoRed,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = if (isSystemInDarkTheme()) ValoBlack else ValoWhite
                ),
                title = {
                    Text(
                        text = "vAlorant",
                        maxLines = 1,
                        fontFamily = FontFamily(Font(R.font.valorant)),
                        color = ValoRed,
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = if (isSystemInDarkTheme()) ValoBlack else ValoWhite
            ) {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute =
                    navBackStackEntry?.destination?.route ?: SBottomNavItems.HOME.route

                SBottomNavItems.entries.forEach { sBottomNavItems ->

                    val isSelected by remember(currentRoute) {
                        derivedStateOf {
                            currentRoute == sBottomNavItems.route::class.qualifiedName
                        }
                    }

                    NavigationBarItem(
                        colors = NavigationBarItemColors(
                            selectedIndicatorColor = ValoRed,
                            selectedIconColor = if (isSystemInDarkTheme()) ValoBlack else ValoWhite,
                            selectedTextColor = ValoRed,
                            unselectedIconColor = if (isSystemInDarkTheme()) ValoGrey else ValoBlack,
                            unselectedTextColor = if (isSystemInDarkTheme()) ValoGrey else ValoBlack,
                            disabledIconColor = ValoBlack,
                            disabledTextColor = ValoBlack
                        ),
                        selected = isSelected,
                        onClick = {
                            navHostController.navigate(sBottomNavItems.route) {
                                popUpTo(navHostController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = sBottomNavItems.icon),
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = sBottomNavItems.label,
                                fontFamily = FontFamily(Font(R.font.aeonik)),
                                fontWeight = FontWeight.Bold
                            )
                        },
                        alwaysShowLabel = false,
                    )
                }
            }

        },
    ) { paddingValues ->
        SetupBottomNavigation(appNavHostController, navHostController, paddingValues)
    }
}