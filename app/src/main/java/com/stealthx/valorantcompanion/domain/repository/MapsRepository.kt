package com.stealthx.valorantcompanion.domain.repository

import com.stealthx.valorantcompanion.data.remote.api.MapsAPI
import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.maps.VMap

interface MapsRepository {
    suspend fun getAllMapsList() : AllResponse<VMap>
}