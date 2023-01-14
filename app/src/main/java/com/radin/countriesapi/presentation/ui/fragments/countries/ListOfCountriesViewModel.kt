package com.radin.countriesapi.presentation.ui.fragments.countries

import com.radin.countriesapi.domain.usecases.GetAllCountriesUseCase
import com.radin.countriesapi.presentation.base.BaseViewModel
import com.radin.countriesapi.presentation.models.CountryUI
import com.radin.countriesapi.presentation.models.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ListOfCountriesViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase
) : BaseViewModel() {

    private val _countriesState = mutableStateFlow<List<CountryUI>>()
    val countriesState = _countriesState.asStateFlow()

    init {
        getAllCountries()
    }

    fun getAllCountries() {
        collectState(
            state = _countriesState,
            request = getAllCountriesUseCase()
        ) {
            it.map { country ->
                country.toUI()
            }
        }
    }
}