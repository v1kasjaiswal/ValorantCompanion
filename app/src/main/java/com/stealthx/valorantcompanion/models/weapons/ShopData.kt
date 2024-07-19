package com.stealthx.valorantcompanion.models.weapons


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShopData(
    @SerialName("categoryText")
    val categoryText: String,

    @SerialName("cost")
    val cost: Int,

    @SerialName("newImage")
    val newImage: String,
)