package com.stealthx.valorantcompanion.domain.repository

import com.stealthx.valorantcompanion.models.agents.Agent
import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.core.DetailResponse

interface AgentRepository {
    suspend fun getAllAgentsList() : AllResponse<Agent>

    suspend fun getAgentDetails(uuid: String) : DetailResponse<Agent>
}