package com.stealthx.valorantcompanion.models.weapons


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Chroma(

    @SerialName("displayIcon")
    val displayIcon: String?,

    @SerialName("displayName")
    val displayName: String,

    @SerialName("fullRender")
    val fullRender: String,

    @SerialName("streamedVideo")
    val streamedVideo: String?,

    @SerialName("uuid")
    val uuid: String
)