package com.stealthx.valorantcompanion.presentation.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stealthx.valorantcompanion.domain.usecases.currencies.GetAllCurrenciesUsecase
import com.stealthx.valorantcompanion.models.currencies.Currency
import com.stealthx.valorantcompanion.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllCurrenciesUsecase: GetAllCurrenciesUsecase
) : ViewModel() {

    val currencyState: StateFlow<Resource<List<Currency>>> =
        getAllCurrenciesUsecase.currencyState.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            Resource.Loading()
        )

    init {
        rogerCurrenciesList()
    }

    private fun rogerCurrenciesList() {
        viewModelScope.launch {
            getAllCurrenciesUsecase()
        }
    }

}