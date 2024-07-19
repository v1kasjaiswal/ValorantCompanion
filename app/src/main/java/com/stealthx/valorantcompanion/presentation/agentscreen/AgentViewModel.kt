package com.stealthx.valorantcompanion.presentation.agentscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stealthx.valorantcompanion.domain.usecases.agents.GetAllAgentsListUsecase
import com.stealthx.valorantcompanion.models.agents.Agent
import com.stealthx.valorantcompanion.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentViewModel @Inject constructor(
    private val getAllAgentsListUsecase: GetAllAgentsListUsecase
) : ViewModel() {

    val agentsState: StateFlow<Resource<List<Agent>>> =
        getAllAgentsListUsecase.agentsState.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            Resource.Loading()
        )

    init {
        rogerAgentsList()
    }

    private fun rogerAgentsList() {
        viewModelScope.launch {
            getAllAgentsListUsecase()
        }
    }
}