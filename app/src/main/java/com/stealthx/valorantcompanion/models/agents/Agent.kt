package com.stealthx.valorantcompanion.models.agents


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Agent(
    @SerialName("abilities")
    val abilities: List<Ability>,

    @SerialName("background")
    val background: String?,

    @SerialName("backgroundGradientColors")
    val backgroundGradientColors: List<String>,

    @SerialName("bustPortrait")
    val bustPortrait: String?,

    @SerialName("characterTags")
    val characterTags: List<String>?,

    @SerialName("description")
    val description: String,

    @SerialName("developerName")
    val developerName: String,

    @SerialName("displayIcon")
    val displayIcon: String,

    @SerialName("displayIconSmall")
    val displayIconSmall: String,

    @SerialName("displayName")
    val displayName: String,

    @SerialName("fullPortrait")
    val fullPortrait: String?,

    @SerialName("fullPortraitV2")
    val fullPortraitV2: String?,

    @SerialName("isAvailableForTest")
    val isAvailableForTest: Boolean,

    @SerialName("isBaseContent")
    val isBaseContent: Boolean,

    @SerialName("isFullPortraitRightFacing")
    val isFullPortraitRightFacing: Boolean,

    @SerialName("isPlayableCharacter")
    val isPlayableCharacter: Boolean,

    @SerialName("killfeedPortrait")
    val killfeedPortrait: String,

    @SerialName("role")
    val role: Role,

    @SerialName("uuid")
    val uuid: String
)