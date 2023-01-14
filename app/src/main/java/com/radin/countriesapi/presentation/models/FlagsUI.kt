package com.radin.countriesapi.presentation.models

import com.radin.countriesapi.domain.models.Flags

data class FlagsUI(
    val svg: String,
    val png: String
)

fun Flags.toUI() = FlagsUI(
    svg = svg,
    png = png
)