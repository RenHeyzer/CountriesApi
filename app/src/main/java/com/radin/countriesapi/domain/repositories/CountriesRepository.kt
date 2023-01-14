package com.radin.countriesapi.domain.repositories

import com.radin.countriesapi.domain.models.Country
import com.radin.countriesapi.domain.either.Either
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {

    fun getAllCountries(): Flow<Either<String, List<Country>>>

    fun getCountryDetail(name: String): Flow<Either<String, List<Country>>>
}