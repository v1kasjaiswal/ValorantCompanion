package com.stealthx.valorantcompanion.navigation.appnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.stealthx.valorantcompanion.presentation.agentdetailsscreen.AgentDetailScreen
import com.stealthx.valorantcompanion.presentation.mainscreen.MainScreen
import com.stealthx.valorantcompanion.presentation.onboardingscreen.OnBoardingScreen
import com.stealthx.valorantcompanion.presentation.splashscreen.SplashScreen
import com.stealthx.valorantcompanion.presentation.weapondetailscreen.WeaponDetailScreen

@Composable
fun SetupNavigation(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(navController = navHostController, startDestination = SplashScreen) {
        composable<SplashScreen> {
            SplashScreen(
                navigateToMain = {
                    navHostController.popBackStack()
                    navHostController.navigate(MainScreen)
                },
                navigateToOnBoard = {
                    navHostController.popBackStack()
                    navHostController.navigate(OnBoardingScreen)
            })
        }

        composable<OnBoardingScreen> {
            OnBoardingScreen(navigateToMain = {
                navHostController.popBackStack()
                navHostController.navigate(MainScreen)
            })
        }

        composable<MainScreen> {
            MainScreen(navHostController)
        }
        
        composable<AgentDetailScreenRoute> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<AgentDetailScreenRoute>()
            AgentDetailScreen(uuid = args.uuid)
        }

        composable<WeaponDetailScreenRoute> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<WeaponDetailScreenRoute>()
            WeaponDetailScreen(uuid = args.uuid)
        }
    }
}