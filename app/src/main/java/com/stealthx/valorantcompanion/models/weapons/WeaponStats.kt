package com.stealthx.valorantcompanion.models.weapons


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeaponStats(
    @SerialName("equipTimeSeconds")
    val equipTimeSeconds: Double,

    @SerialName("fireRate")
    val fireRate: Double,

    @SerialName("firstBulletAccuracy")
    val firstBulletAccuracy: Double,

    @SerialName("magazineSize")
    val magazineSize: Int,

    @SerialName("reloadTimeSeconds")
    val reloadTimeSeconds: Double,

    @SerialName("runSpeedMultiplier")
    val runSpeedMultiplier: Double,

    @SerialName("wallPenetration")
    val wallPenetration: String
)