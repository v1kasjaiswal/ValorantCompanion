package com.stealthx.valorantcompanion.models.weapons


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Skin(
    @SerialName("chromas")
    val chromas: List<Chroma>,

    @SerialName("displayIcon")
    val displayIcon: String?,

    @SerialName("displayName")
    val displayName: String,

    @SerialName("levels")
    val levels: List<Level>,

    @SerialName("uuid")
    val uuid: String,
)