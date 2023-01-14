package com.radin.countriesapi.presentation.base

import androidx.recyclerview.widget.DiffUtil

/**
 * Base class for [DiffUtil]
 * Data is compared via interface
 */
class BaseDiffUtilItemCallback<Type, T : IBaseDiffModel<Type>> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}