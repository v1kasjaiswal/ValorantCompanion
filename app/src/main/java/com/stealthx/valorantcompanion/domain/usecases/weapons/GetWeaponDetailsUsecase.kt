package com.stealthx.valorantcompanion.domain.usecases.weapons

import com.stealthx.valorantcompanion.domain.repository.WeaponsRepository
import com.stealthx.valorantcompanion.models.weapons.Weapon
import com.stealthx.valorantcompanion.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class GetWeaponDetailsUsecase @Inject constructor(
    private val weaponsRepository: WeaponsRepository
) {
    private val _weaponState = MutableStateFlow<Resource<Weapon>>(Resource.Loading())
    val weaponState: StateFlow<Resource<Weapon>> = _weaponState

    suspend operator fun invoke(uuid: String) {
        _weaponState.update { Resource.Loading() }

        try {
            val weaponDetail = weaponsRepository.getWeaponDetails(uuid).data
            _weaponState.update { Resource.Success(data = weaponDetail) }
        }
        catch (e: Exception) {
            _weaponState.update { Resource.Error(message = e.localizedMessage ?: "Something went Wrong!") }
        }
    }
}