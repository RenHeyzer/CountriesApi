package com.radin.countriesapi.presentation.ui.fragments.countries.detail

import com.radin.countriesapi.domain.usecases.GetCountryDetailUseCase
import com.radin.countriesapi.presentation.base.BaseViewModel
import com.radin.countriesapi.presentation.models.CountryUI
import com.radin.countriesapi.presentation.models.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val getCountryDetailUseCase: GetCountryDetailUseCase
) : BaseViewModel() {

    private val _countryDetailState = mutableStateFlow<List<CountryUI>>()
    val countryDetailState = _countryDetailState.asStateFlow()

    fun getCountryDetail(name: String) {
        collectState(
            state = _countryDetailState,
            request = getCountryDetailUseCase(name),
            mapping = {
                it.map { country ->
                    country.toUI()
                }
            }
        )
    }
}