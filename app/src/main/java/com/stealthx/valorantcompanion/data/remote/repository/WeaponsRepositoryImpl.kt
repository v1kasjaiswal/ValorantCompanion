package com.stealthx.valorantcompanion.data.remote.repository

import com.stealthx.valorantcompanion.data.remote.api.WeaponsAPI
import com.stealthx.valorantcompanion.domain.repository.WeaponsRepository
import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.core.DetailResponse
import com.stealthx.valorantcompanion.models.weapons.Weapon
import javax.inject.Inject

class WeaponsRepositoryImpl @Inject constructor(
    private val weaponsAPI: WeaponsAPI
) : WeaponsRepository {
    override suspend fun getAllWeaponsList(): AllResponse<Weapon> {
        return weaponsAPI.getAllWeaponsList()
    }

    override suspend fun getWeaponDetails(uuid: String): DetailResponse<Weapon> {
        return weaponsAPI.getWeaponDetails(uuid)
    }
}