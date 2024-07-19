package com.stealthx.valorantcompanion.domain.onboarding.usecases

import com.stealthx.valorantcompanion.domain.onboarding.AppDatastore

class SaveOnBoardPreferenceUsecase(
    private val appDatastore: AppDatastore
) {
    suspend operator fun invoke() {
        appDatastore.saveOnBoardingPref()
    }
}