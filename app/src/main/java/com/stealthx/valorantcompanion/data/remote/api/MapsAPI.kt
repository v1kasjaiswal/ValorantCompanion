package com.stealthx.valorantcompanion.data.remote.api

import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.maps.VMap
import com.stealthx.valorantcompanion.navigation.bottomnavigation.Maps
import retrofit2.http.GET
import retrofit2.http.Query

interface MapsAPI {
    @GET("maps/")
    suspend fun getAllMapsList() : AllResponse<VMap>
}