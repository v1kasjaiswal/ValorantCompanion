package com.stealthx.valorantcompanion.data.remote.repository

import com.stealthx.valorantcompanion.data.remote.api.AgentsAPI
import com.stealthx.valorantcompanion.domain.repository.AgentRepository
import com.stealthx.valorantcompanion.models.agents.Agent
import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.core.DetailResponse

class AgentRepositoryImpl(
    private val agentsAPI: AgentsAPI
) : AgentRepository {
    override suspend fun getAllAgentsList(): AllResponse<Agent> {
        return agentsAPI.getAllAgentsList(true)
    }

    override suspend fun getAgentDetails(uuid: String): DetailResponse<Agent> {
        return agentsAPI.getAgentDetails(uuid)
    }
}