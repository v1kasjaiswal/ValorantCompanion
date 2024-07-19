package com.stealthx.valorantcompanion.navigation.bottomnavigation.utils

import androidx.annotation.DrawableRes
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.navigation.bottomnavigation.Agents
import com.stealthx.valorantcompanion.navigation.bottomnavigation.Home
import com.stealthx.valorantcompanion.navigation.bottomnavigation.Maps
import com.stealthx.valorantcompanion.navigation.bottomnavigation.Modes
import com.stealthx.valorantcompanion.navigation.bottomnavigation.Weapons

enum class SBottomNavItems(
    val label: String,
    @DrawableRes val icon: Int,
    val route: Any
) {
    HOME("Home", R.drawable.home_icon, Home),
    WEAPONS("Weapons", R.drawable.weapon_icon, Weapons),
    AGENTS("Agents", R.drawable.agent_icon, Agents),
    MAPS("Maps", R.drawable.map_icon, Maps),
    RANKS("Modes", R.drawable.mode_icon, Modes),
}