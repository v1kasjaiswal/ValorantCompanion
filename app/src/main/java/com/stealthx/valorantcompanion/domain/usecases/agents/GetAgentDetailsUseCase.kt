package com.stealthx.valorantcompanion.domain.usecases.agents

import android.util.Log
import com.stealthx.valorantcompanion.domain.repository.AgentRepository
import com.stealthx.valorantcompanion.models.agents.Agent
import com.stealthx.valorantcompanion.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class GetAgentDetailsUseCase @Inject constructor(
    private val agentRepository: AgentRepository
){
    private val _agentState = MutableStateFlow<Resource<Agent>>(Resource.Loading())
    val agentState = _agentState.asStateFlow()

    suspend operator  fun  invoke(uuid: String) {
        _agentState.update { Resource.Loading() }

        try {
            val agentDetail = agentRepository.getAgentDetails(uuid).data
            _agentState.update { Resource.Success(data = agentDetail) }
        }
        catch (e: Exception) {
            _agentState.update { Resource.Error(message = e.localizedMessage ?: "Something Went Wrong") }
        }
    }
}