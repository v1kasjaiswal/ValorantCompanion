package com.stealthx.valorantcompanion.models.currencies


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    @SerialName("displayIcon")
    val displayIcon: String,

    @SerialName("displayNameSingular")
    val displayNameSingular: String,

    @SerialName("largeIcon")
    val largeIcon: String,
)