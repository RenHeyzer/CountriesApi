package com.radin.countriesapi.data.remote.api

import com.radin.countriesapi.data.models.CountryDto
import com.radin.countriesapi.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApiService {

    @GET(Constants.ALL_ENDPOINT)
    suspend fun getAllCountries(): List<CountryDto>

    @GET(Constants.BY_NAME_ENDPOINT)
    suspend fun getCountryDetail(
        @Path("name") name: String
    ): List<CountryDto>
}