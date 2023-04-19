package com.radin.countriesapi.presentation.ui.fragments.countries

import androidx.lifecycle.SavedStateHandle
import com.radin.countriesapi.domain.usecases.GetAllCountriesUseCase
import com.radin.countriesapi.domain.usecases.SearchCountryByNameUseCase
import com.radin.countriesapi.presentation.base.BaseViewModel
import com.radin.countriesapi.presentation.models.CountryUI
import com.radin.countriesapi.presentation.models.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class ListOfCountriesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val searchCountryByNameUseCase: SearchCountryByNameUseCase
) : BaseViewModel() {

    private val _countriesState = mutableStateFlow<List<CountryUI>>()
    val countriesState = _countriesState.asStateFlow()

    private val filteredData: StateFlow<String> =
        savedStateHandle.getStateFlow("query", "")

    private val refreshData: StateFlow<String> =
        savedStateHandle.getStateFlow("refresh", "")

    init {
        getAllCountries()

        searchCountryByName()
    }

    fun setQuery(query: String) {
        savedStateHandle["query"] = query
    }

    fun setRefreshKey(key: String) {
        savedStateHandle["refresh"] = key
    }

    private fun getAllCountries() {
        refreshData.flatMapLatest { refresh ->
            getAllCountriesUseCase()
        }.also { request ->
            collectState(
                state = _countriesState,
                request = request
            ) {
                it.map { country ->
                    country.toUI()
                }
            }
        }
    }

    private fun searchCountryByName() {
        filteredData.flatMapLatest { query ->
            searchCountryByNameUseCase(query)
        }.also { request ->
            collectState(
                state = _countriesState,
                request = request
            ) {
                it.map { country ->
                    country.toUI()
                }
            }
        }
    }
}