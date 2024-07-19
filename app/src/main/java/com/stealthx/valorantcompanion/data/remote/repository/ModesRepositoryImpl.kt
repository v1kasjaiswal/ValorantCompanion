package com.stealthx.valorantcompanion.data.remote.repository

import com.stealthx.valorantcompanion.data.remote.api.ModesAPI
import com.stealthx.valorantcompanion.domain.repository.ModesRepository
import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.modes.Mode
import javax.inject.Inject

class ModesRepositoryImpl @Inject constructor(
    private val modesAPI: ModesAPI
) : ModesRepository{
    override suspend fun getAllModes(): AllResponse<Mode> {
        return modesAPI.getAllModes()
    }
}