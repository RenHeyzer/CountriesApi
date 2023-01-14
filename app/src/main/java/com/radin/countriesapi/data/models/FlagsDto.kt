package com.radin.countriesapi.data.models

import com.google.gson.annotations.SerializedName
import com.radin.countriesapi.domain.models.Flags

data class FlagsDto(
    @SerializedName("svg")
    val svg: String,
    @SerializedName("png")
    val png: String
)

fun FlagsDto.toDomain() = Flags(
    svg = svg,
    png = png
)