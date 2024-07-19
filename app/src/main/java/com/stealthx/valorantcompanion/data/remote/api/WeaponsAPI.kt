package com.stealthx.valorantcompanion.data.remote.api

import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.core.DetailResponse
import com.stealthx.valorantcompanion.models.weapons.Weapon
import retrofit2.http.GET
import retrofit2.http.Path

interface WeaponsAPI {
    @GET("weapons/")
    suspend fun getAllWeaponsList(): AllResponse<Weapon>

    @GET("weapons/{uuid}")
    suspend fun getWeaponDetails(@Path("uuid") uuid: String): DetailResponse<Weapon>
}