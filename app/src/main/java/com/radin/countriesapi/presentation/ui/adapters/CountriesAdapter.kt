package com.radin.countriesapi.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.radin.countriesapi.databinding.ItemCountryBinding
import com.radin.countriesapi.presentation.base.BaseDiffUtilItemCallback
import com.radin.countriesapi.presentation.extensions.loadImage
import com.radin.countriesapi.presentation.models.CountryUI

class CountriesAdapter(val onItemClick: (name: String) -> Unit) :
    ListAdapter<CountryUI, CountriesAdapter.CountryViewHolder>(
        BaseDiffUtilItemCallback()
    ), View.OnClickListener {

    inner class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: CountryUI) = with(binding) {
            ivCountryFlag.loadImage(item.flags.png)
            tvCountryName.text = item.name
            itemView.tag = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.onBind(getItem(position))
        holder.itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let { onItemClick(it.tag as String) }
    }
}