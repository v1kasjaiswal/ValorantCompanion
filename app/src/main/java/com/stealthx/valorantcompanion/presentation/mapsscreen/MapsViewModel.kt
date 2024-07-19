package com.stealthx.valorantcompanion.presentation.mapsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stealthx.valorantcompanion.domain.usecases.maps.GetAllMapsUsecase
import com.stealthx.valorantcompanion.models.maps.VMap
import com.stealthx.valorantcompanion.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val getAllMapsUsecase: GetAllMapsUsecase
) : ViewModel() {

    val mapsState: StateFlow<Resource<List<VMap>>> =
        getAllMapsUsecase.mapsState.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            Resource.Loading()
        )

    init {
        rogerMapsList()
    }

    private fun rogerMapsList() {
        viewModelScope.launch {
            getAllMapsUsecase()
        }
    }
}