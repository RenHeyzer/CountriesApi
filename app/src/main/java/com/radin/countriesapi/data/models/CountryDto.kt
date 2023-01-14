package com.radin.countriesapi.data.models

import com.google.gson.annotations.SerializedName
import com.radin.countriesapi.domain.models.Country

data class CountryDto(
    @SerializedName("nativeName")
    val nativeName: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("alpha2Code")
    val alpha2Code: String,
    @SerializedName("independent")
    val independent: Boolean,
    @SerializedName("flags")
    val flags: FlagsDto,
    @SerializedName("alpha3Code")
    val alpha3Code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("capital")
    val capital: String? = null,
    @SerializedName("currencies")
    val currencies: List<CurrencyDto>? = null,
    @SerializedName("timezones")
    val timezones: List<String>? = null
)

fun CountryDto.toDomain() = Country(
    nativeName = nativeName,
    flag = flag,
    alpha2Code = alpha2Code,
    independent = independent,
    flags = flags.toDomain(),
    alpha3Code = alpha3Code,
    name = name,
    region = region,
    capital = capital,
    currencies = currencies?.map {
        it.toDomain()
    },
    timezones = timezones
)