package com.radin.countriesapi.presentation.ui.fragments.countries

import android.view.View
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.radin.countriesapi.R
import com.radin.countriesapi.databinding.FragmentListOfCountiresBinding
import com.radin.countriesapi.presentation.base.BaseFragment
import com.radin.countriesapi.presentation.extensions.navigateSafely
import com.radin.countriesapi.presentation.ui.adapters.CountriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfCountriesFragment :
    BaseFragment<FragmentListOfCountiresBinding, ListOfCountriesViewModel>(
        R.layout.fragment_list_of_countires
    ) {
    override val binding by viewBinding(FragmentListOfCountiresBinding::bind)
    override val viewModel by viewModels<ListOfCountriesViewModel>()
    private val countriesAdapter = CountriesAdapter(this::onItemClick)

    override fun initialize() {
        binding.rvCountry.adapter = countriesAdapter
    }

    override fun setupListeners() {
        binding.btnRefresh.setOnClickListener {
            viewModel.getAllCountries()
            setupVisibility(p1 = false, p2 = true)
        }
    }

    override fun setupCollects(view: View) {
        viewModel.countriesState.collectUIState(
            loading = {
                setupVisibility(p1 = false, p2 = true)
            },
            success = { data ->
                setupVisibility(p1 = true, p2 = false)
                countriesAdapter.submitList(data)
            },
            error = { message ->
                Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
                setupVisibility(p1 = true, p2 = false)
            }
        )
    }

    private fun setupVisibility(p1: Boolean, p2: Boolean) {
        binding.root.forEach { v ->
            if (v.id != R.id.pb_loading) {
                v.isVisible = p1
            } else {
                v.isVisible = p2
            }
        }
    }

    private fun onItemClick(name: String) {
        findNavController().navigateSafely(
            ListOfCountriesFragmentDirections.actionListOfCountiresFragmentToCountryDetailFragment(
                name = name
            )
        )
    }
}