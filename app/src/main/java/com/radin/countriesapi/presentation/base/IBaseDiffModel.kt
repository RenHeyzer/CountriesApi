package com.radin.countriesapi.presentation.base

import androidx.recyclerview.widget.DiffUtil

/**
 * [DiffUtil] interface
 */
interface IBaseDiffModel<Type> {
    val name: Type?
    override fun equals(other: Any?): Boolean
}