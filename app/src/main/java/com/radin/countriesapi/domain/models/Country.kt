package com.radin.countriesapi.domain.models

data class Country(
    val nativeName: String,
    val flag: String,
    val alpha2Code: String,
    val independent: Boolean,
    val flags: Flags,
    val alpha3Code: String,
    val name: String,
    val region: String,
    val capital: String? = null,
    val currencies: List<Currency>? = null,
    val timezones: List<String>? = null
)