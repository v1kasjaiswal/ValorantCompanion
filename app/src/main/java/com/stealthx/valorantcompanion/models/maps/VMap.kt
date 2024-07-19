package com.stealthx.valorantcompanion.models.maps


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VMap(
    @SerialName("coordinates")
    val coordinates: String?,

    @SerialName("displayIcon")
    val displayIcon: String?,

    @SerialName("displayName")
    val displayName: String,

    @SerialName("listViewIcon")
    val listViewIcon: String,

    @SerialName("listViewIconTall")
    val listViewIconTall: String,

    @SerialName("splash")
    val splash: String,

    @SerialName("tacticalDescription")
    val tacticalDescription: String?,

    @SerialName("uuid")
    val uuid: String,
)