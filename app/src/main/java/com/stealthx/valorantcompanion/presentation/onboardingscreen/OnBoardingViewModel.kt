package com.stealthx.valorantcompanion.presentation.onboardingscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stealthx.valorantcompanion.domain.onboarding.usecases.SaveOnBoardPreferenceUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveOnBoardPreferenceUsecase: SaveOnBoardPreferenceUsecase
) : ViewModel() {
    fun saveOnBoardPref() {
        viewModelScope.launch {
            saveOnBoardPreferenceUsecase()
        }
    }
}