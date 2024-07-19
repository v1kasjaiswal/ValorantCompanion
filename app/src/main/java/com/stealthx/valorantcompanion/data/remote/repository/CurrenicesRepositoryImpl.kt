package com.stealthx.valorantcompanion.data.remote.repository

import com.stealthx.valorantcompanion.data.remote.api.CurrenciesAPI
import com.stealthx.valorantcompanion.domain.repository.CurrenciesRepository
import com.stealthx.valorantcompanion.models.core.AllResponse
import com.stealthx.valorantcompanion.models.currencies.Currency
import javax.inject.Inject

class CurrenicesRepositoryImpl @Inject constructor(
    private val currenciesAPI: CurrenciesAPI
): CurrenciesRepository {
    override suspend fun getAllCurrenciesList(): AllResponse<Currency> {
        return currenciesAPI.getAllCurrenciesList()
    }
}