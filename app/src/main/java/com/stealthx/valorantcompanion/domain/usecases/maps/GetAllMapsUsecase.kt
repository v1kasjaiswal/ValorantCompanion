package com.stealthx.valorantcompanion.domain.usecases.maps

import com.stealthx.valorantcompanion.domain.repository.MapsRepository
import com.stealthx.valorantcompanion.models.maps.VMap
import com.stealthx.valorantcompanion.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class GetAllMapsUsecase @Inject constructor(
    private val mapsRepository: MapsRepository
) {
    private val _mapsState = MutableStateFlow<Resource<List<VMap>>>(Resource.Loading())
    val mapsState: StateFlow<Resource<List<VMap>>> = _mapsState

    suspend operator fun invoke() {
        _mapsState.update { Resource.Loading() }

        try {
            val mapsList = mapsRepository.getAllMapsList().data

            _mapsState.update { Resource.Success(data = mapsList) }
        }
        catch (e: Exception) {
            _mapsState.update { Resource.Error(message = e.localizedMessage ?: "Something Went Wrong!") }
        }
    }
 }
