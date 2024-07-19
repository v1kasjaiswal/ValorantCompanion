package com.stealthx.valorantcompanion.domain.usecases.weapons

import com.stealthx.valorantcompanion.domain.repository.WeaponsRepository
import com.stealthx.valorantcompanion.models.weapons.Weapon
import com.stealthx.valorantcompanion.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class GetAllWeaponsUsecase @Inject constructor(
    private val weaponsRepository: WeaponsRepository
) {
    private val _weaponsState = MutableStateFlow<Resource<List<Weapon>>>(Resource.Loading())
    val weaponsState: StateFlow<Resource<List<Weapon>>> = _weaponsState

    suspend operator fun invoke() {
        _weaponsState.update { Resource.Loading() }

        try {
            val weaponsList = weaponsRepository.getAllWeaponsList().data

            _weaponsState.update { Resource.Success(data = weaponsList) }
        }
        catch (e: Exception) {
            _weaponsState.update { Resource.Error(message = e.localizedMessage ?: "Something went Wrong!") }
        }
    }
}