package com.radin.countriesapi.presentation.models

import com.radin.countriesapi.domain.models.Currency

data class CurrencyUI(
    val symbol: String? = null,
    val code: String? = null,
    val name: String? = null
)

fun Currency.toUI() = CurrencyUI(
    symbol = symbol,
    code = code,
    name = name
)