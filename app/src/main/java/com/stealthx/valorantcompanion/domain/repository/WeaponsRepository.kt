package com.stealthx.valorantcompanion.domain.repository

import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.core.DetailResponse
import com.stealthx.valorantcompanion.models.weapons.Weapon

interface WeaponsRepository {
    suspend fun getAllWeaponsList(): AllResponse<Weapon>

    suspend fun getWeaponDetails(uuid: String): DetailResponse<Weapon>
}