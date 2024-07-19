package com.stealthx.valorantcompanion.models.modes


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mode(
    @SerialName("allowsMatchTimeouts")
    val allowsMatchTimeouts: Boolean,

    @SerialName("description")
    val description: String?,

    @SerialName("displayIcon")
    val displayIcon: String?,

    @SerialName("displayName")
    val displayName: String,

    @SerialName("duration")
    val duration: String?,

    @SerialName("isMinimapHidden")
    val isMinimapHidden: Boolean,

    @SerialName("isTeamVoiceAllowed")
    val isTeamVoiceAllowed: Boolean,

    @SerialName("listViewIconTall")
    val listViewIconTall: String?,

    @SerialName("uuid")
    val uuid: String
)