package com.stealthx.valorantcompanion.models.weapons


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Level(
    @SerialName("displayIcon")
    val displayIcon: String?,

    @SerialName("displayName")
    val displayName: String,

    @SerialName("levelItem")
    val levelItem: String?,

    @SerialName("streamedVideo")
    val streamedVideo: String?,

    @SerialName("uuid")
    val uuid: String
)