package com.stealthx.valorantcompanion.data.remote.api

import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.currencies.Currency
import retrofit2.http.GET

interface CurrenciesAPI {
    @GET("currencies/")
    suspend fun getAllCurrenciesList(): AllResponse<Currency>
}