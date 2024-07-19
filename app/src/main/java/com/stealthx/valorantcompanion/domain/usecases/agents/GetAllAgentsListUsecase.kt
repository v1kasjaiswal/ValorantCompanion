package com.stealthx.valorantcompanion.domain.usecases.agents

import android.util.Log
import com.stealthx.valorantcompanion.domain.repository.AgentRepository
import com.stealthx.valorantcompanion.models.agents.Agent
import com.stealthx.valorantcompanion.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.HttpException
import javax.inject.Inject

class GetAllAgentsListUsecase @Inject constructor(
    private val agentRepository: AgentRepository
) {
    private val _agentsState = MutableStateFlow<Resource<List<Agent>>>(Resource.Loading())
    val agentsState = _agentsState.asStateFlow()

    suspend operator fun invoke() {
        _agentsState.update { Resource.Loading() }

        try {
            val agentsList = agentRepository.getAllAgentsList().data


            _agentsState.update { Resource.Success(data = agentsList) }
        }
        catch (e: Exception) {
            _agentsState.update { Resource.Error(message = e.localizedMessage ?: "Something Went Wrong!") }
        }
    }
}