package com.stealthx.valorantcompanion.models.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllResponse<T> (
    @SerialName("status")
    val status: Int,

    @SerialName("data")
    val data: List<T>
)