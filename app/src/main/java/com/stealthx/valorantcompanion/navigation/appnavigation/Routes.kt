package com.stealthx.valorantcompanion.navigation.appnavigation

import kotlinx.serialization.Serializable

@Serializable
object SplashScreen

@Serializable
object OnBoardingScreen

@Serializable
object MainScreen

@Serializable
data class AgentDetailScreenRoute(val uuid: String)

@Serializable
data class WeaponDetailScreenRoute(val uuid: String)
