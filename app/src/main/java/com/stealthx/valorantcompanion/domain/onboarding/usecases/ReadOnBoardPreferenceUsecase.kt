package com.stealthx.valorantcompanion.domain.onboarding.usecases

import com.stealthx.valorantcompanion.domain.onboarding.AppDatastore
import kotlinx.coroutines.flow.Flow

class ReadOnBoardPreferenceUsecase(
    private val appDatastore: AppDatastore
) {
    operator fun invoke(): Flow<Boolean> {
        return appDatastore.readOnBoardingPref()
    }
}