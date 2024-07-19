package com.stealthx.valorantcompanion.data.remote.repository

import com.stealthx.valorantcompanion.data.remote.api.MapsAPI
import com.stealthx.valorantcompanion.domain.repository.MapsRepository
import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.maps.VMap
import javax.inject.Inject

class MapsRepositoryImpl @Inject constructor(
    private val mapsAPI: MapsAPI
) : MapsRepository {
    override suspend fun getAllMapsList(): AllResponse<VMap> {
        return mapsAPI.getAllMapsList()
    }
}