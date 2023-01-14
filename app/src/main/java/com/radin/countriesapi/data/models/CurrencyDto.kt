package com.radin.countriesapi.data.models

import com.google.gson.annotations.SerializedName
import com.radin.countriesapi.domain.models.Currency

data class CurrencyDto(
    @SerializedName("symbol")
    val symbol: String? = null,
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("name")
    val name: String? = null
)

fun CurrencyDto.toDomain() = Currency(
    symbol = symbol,
    code = code,
    name = name
)