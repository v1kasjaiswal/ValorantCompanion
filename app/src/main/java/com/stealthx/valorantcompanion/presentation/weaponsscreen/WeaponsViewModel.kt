package com.stealthx.valorantcompanion.presentation.weaponsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stealthx.valorantcompanion.domain.usecases.weapons.GetAllWeaponsUsecase
import com.stealthx.valorantcompanion.models.weapons.Weapon
import com.stealthx.valorantcompanion.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeaponsViewModel @Inject constructor(
    private val getAllWeaponsUsecase: GetAllWeaponsUsecase
) : ViewModel() {
    val weaponsState: StateFlow<Resource<List<Weapon>>> =
        getAllWeaponsUsecase.weaponsState.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            Resource.Loading()
        )

    init {
        rogerWeaponsList()
    }

    private fun rogerWeaponsList() {
        viewModelScope.launch {
            getAllWeaponsUsecase()
        }
    }
}