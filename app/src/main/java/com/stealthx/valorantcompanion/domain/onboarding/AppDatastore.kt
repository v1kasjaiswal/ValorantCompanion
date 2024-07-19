package com.stealthx.valorantcompanion.domain.onboarding

import kotlinx.coroutines.flow.Flow

interface AppDatastore {
    fun readOnBoardingPref() : Flow<Boolean>

    suspend fun saveOnBoardingPref()
}