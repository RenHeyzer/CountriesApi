package com.radin.countriesapi.domain.usecases

import com.radin.countriesapi.domain.repositories.CountriesRepository
import javax.inject.Inject

class GetCountryDetailUseCase @Inject constructor(
    private val repository: CountriesRepository
) {

    operator fun invoke(name: String) = repository.getCountryDetail(name)
}