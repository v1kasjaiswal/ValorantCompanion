package com.stealthx.valorantcompanion.models.weapons


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weapon(
    @SerialName("category")
    val category: String,

    @SerialName("defaultSkinUuid")
    val defaultSkinUuid: String,

    @SerialName("displayIcon")
    val displayIcon: String,

    @SerialName("displayName")
    val displayName: String,

    @SerialName("killStreamIcon")
    val killStreamIcon: String,

    @SerialName("shopData")
    val shopData: ShopData?,

    @SerialName("skins")
    val skins: List<Skin>,

    @SerialName("uuid")
    val uuid: String,

    @SerialName("weaponStats")
    val weaponStats: WeaponStats?
)