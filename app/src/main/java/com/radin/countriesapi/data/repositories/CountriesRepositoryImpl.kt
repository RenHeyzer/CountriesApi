package com.radin.countriesapi.data.repositories

import com.radin.countriesapi.data.base.BaseRepository
import com.radin.countriesapi.data.models.toDomain
import com.radin.countriesapi.data.remote.api.CountriesApiService
import com.radin.countriesapi.domain.repositories.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val countriesApiService: CountriesApiService
) : BaseRepository(), CountriesRepository {

    override fun getAllCountries() = doRequest {
        countriesApiService.getAllCountries().map {
            it.toDomain()
        }
    }

    override fun getCountryDetail(name: String) = doRequest {
        countriesApiService.getCountryDetail(name).map {
            it.toDomain()
        }
    }
}