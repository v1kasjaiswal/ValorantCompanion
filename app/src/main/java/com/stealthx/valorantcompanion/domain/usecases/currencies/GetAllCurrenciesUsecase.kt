package com.stealthx.valorantcompanion.domain.usecases.currencies

import androidx.compose.runtime.MutableState
import com.stealthx.valorantcompanion.domain.repository.CurrenciesRepository
import com.stealthx.valorantcompanion.models.currencies.Currency
import com.stealthx.valorantcompanion.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class GetAllCurrenciesUsecase @Inject constructor(
    private val currenciesRepository: CurrenciesRepository
) {
    private val _currencyState = MutableStateFlow<Resource<List<Currency>>>(Resource.Loading())
    val currencyState: StateFlow<Resource<List<Currency>>> = _currencyState

    suspend operator fun invoke() {
        _currencyState.update { Resource.Loading() }

        try {
            val currenciesList = currenciesRepository.getAllCurrenciesList().data

            _currencyState.update { Resource.Success(data = currenciesList) }
        }
        catch (e: Exception) {
            _currencyState.update { Resource.Error(message = e.localizedMessage ?: "Something went Wrong!") }
        }
    }
}