package com.stealthx.valorantcompanion.domain.repository

import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.currencies.Currency

interface CurrenciesRepository {
    suspend fun getAllCurrenciesList(): AllResponse<Currency>
}