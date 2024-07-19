package com.stealthx.valorantcompanion.models.agents


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Role(
    @SerialName("description")
    val description: String,

    @SerialName("displayIcon")
    val displayIcon: String,

    @SerialName("displayName")
    val displayName: String,

    @SerialName("uuid")
    val uuid: String
)