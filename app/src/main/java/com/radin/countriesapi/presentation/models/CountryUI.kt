package com.radin.countriesapi.presentation.models

import com.radin.countriesapi.domain.models.Country
import com.radin.countriesapi.presentation.base.IBaseDiffModel

data class CountryUI(
    val nativeName: String,
    val flag: String,
    val alpha2Code: String,
    val independent: Boolean,
    val flags: FlagsUI,
    val alpha3Code: String,
    override val name: String,
    val region: String,
    val capital: String? = null,
    val currencies: List<CurrencyUI>? = null,
    val timezones: List<String>? = null
) : IBaseDiffModel<String>

fun Country.toUI() = CountryUI(
    nativeName = nativeName,
    flag = flag,
    alpha2Code = alpha2Code,
    independent = independent,
    flags = flags.toUI(),
    alpha3Code = alpha3Code,
    name = name,
    region = region,
    capital = capital,
    currencies = currencies?.map {
        it.toUI()
    },
    timezones = timezones
)