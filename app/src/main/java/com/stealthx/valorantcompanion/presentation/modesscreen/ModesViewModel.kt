package com.stealthx.valorantcompanion.presentation.modesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stealthx.valorantcompanion.domain.usecases.modes.GetAllModesUsecase
import com.stealthx.valorantcompanion.models.modes.Mode
import com.stealthx.valorantcompanion.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModesViewModel @Inject constructor(
    private val getAllModesUsecase: GetAllModesUsecase
) : ViewModel() {
    val modesState: StateFlow<Resource<List<Mode>>> =
        getAllModesUsecase.modesState.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            Resource.Loading()
        )

    init {
        rogerModesList()
    }

    private fun rogerModesList() {
        viewModelScope.launch {
            getAllModesUsecase()
        }
    }
}