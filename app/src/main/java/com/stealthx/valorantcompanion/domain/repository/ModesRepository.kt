package com.stealthx.valorantcompanion.domain.repository

import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.modes.Mode

interface ModesRepository {
    suspend fun getAllModes() : AllResponse<Mode>
}