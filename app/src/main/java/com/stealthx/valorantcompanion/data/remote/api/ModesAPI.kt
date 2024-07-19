package com.stealthx.valorantcompanion.data.remote.api

import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.modes.Mode
import retrofit2.http.GET

interface ModesAPI {
    @GET("gamemodes/")
    suspend fun getAllModes() : AllResponse<Mode>
}