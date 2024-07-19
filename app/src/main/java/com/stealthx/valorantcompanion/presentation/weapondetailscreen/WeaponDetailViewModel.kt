package com.stealthx.valorantcompanion.presentation.weapondetailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stealthx.valorantcompanion.domain.usecases.weapons.GetWeaponDetailsUsecase
import com.stealthx.valorantcompanion.models.weapons.Weapon
import com.stealthx.valorantcompanion.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeaponDetailViewModel @Inject constructor(
    private val getWeaponDetailsUsecase: GetWeaponDetailsUsecase
): ViewModel() {
    val weaponDetailState: StateFlow<Resource<Weapon>> =
        getWeaponDetailsUsecase.weaponState.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            Resource.Loading()
        )

    fun rogerWeaponDetails(uuid: String) {
        viewModelScope.launch {
            getWeaponDetailsUsecase(uuid)
        }
    }
}