package com.stealthx.valorantcompanion.data.remote.api

import com.stealthx.valorantcompanion.models.agents.Agent
import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.core.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AgentsAPI {
    @GET("agents/")
    suspend fun getAllAgentsList(@Query("isPlayableCharacter")  isPlayableCharacter: Boolean) : AllResponse<Agent>

    @GET("agents/{uuid}")
    suspend fun getAgentDetails(@Path("uuid") uuid: String) : DetailResponse<Agent>
}