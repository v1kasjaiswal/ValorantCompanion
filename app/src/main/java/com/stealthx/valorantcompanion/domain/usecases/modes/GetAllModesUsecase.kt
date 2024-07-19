package com.stealthx.valorantcompanion.domain.usecases.modes

import android.util.Log
import com.stealthx.valorantcompanion.domain.repository.ModesRepository
import com.stealthx.valorantcompanion.models.modes.Mode
import com.stealthx.valorantcompanion.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class GetAllModesUsecase @Inject constructor(
    private val modesRepository: ModesRepository
) {
    private val _modesState = MutableStateFlow<Resource<List<Mode>>>(Resource.Loading())
    val modesState: StateFlow<Resource<List<Mode>>> = _modesState

    suspend operator fun invoke() {
        _modesState.update { Resource.Loading() }

        try {
            val modesList = modesRepository.getAllModes().data

            _modesState.update { Resource.Success(data = modesList) }
        }
        catch (e: Exception) {
            Log.d("VALOANTX", e.toString())
            _modesState.update { Resource.Error(message = e.localizedMessage ?: "Something went Wrong!") }
        }
    }
}