package com.stealthx.valorantcompanion.presentation.agentdetailsscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stealthx.valorantcompanion.domain.usecases.agents.GetAgentDetailsUseCase
import com.stealthx.valorantcompanion.models.agents.Agent
import com.stealthx.valorantcompanion.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentDetailViewModel @Inject constructor(
    private val getAgentDetailsUseCase: GetAgentDetailsUseCase
) : ViewModel() {

    val agentDetailState: StateFlow<Resource<Agent>> =
        getAgentDetailsUseCase.agentState.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            Resource.Loading()
        )

    fun rogerAgentDetails(uuid: String) {
        viewModelScope.launch {
            getAgentDetailsUseCase(uuid)
        }
    }
}