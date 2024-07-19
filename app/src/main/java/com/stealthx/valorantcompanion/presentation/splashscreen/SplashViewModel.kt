package com.stealthx.valorantcompanion.presentation.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stealthx.valorantcompanion.domain.onboarding.usecases.ReadOnBoardPreferenceUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val readOnBoardPreferenceUsecase: ReadOnBoardPreferenceUsecase) : ViewModel() {
    private val _onBoardPref = MutableStateFlow<Boolean>(false)
    val onBoardPref: StateFlow<Boolean> = _onBoardPref

    init {
        readOnBoardPref()
    }

    private fun readOnBoardPref() {
        viewModelScope.launch {
            readOnBoardPreferenceUsecase().collect{ value ->
                _onBoardPref.value = value
            }
        }
    }
}