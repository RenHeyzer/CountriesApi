package com.radin.countriesapi.presentation.ui.fragments.countries.detail

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.radin.countriesapi.R
import com.radin.countriesapi.databinding.FragmentCountryDetailBinding
import com.radin.countriesapi.presentation.base.BaseFragment
import com.radin.countriesapi.presentation.extensions.loadRoundImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailFragment : BaseFragment<FragmentCountryDetailBinding, CountryDetailViewModel>(
    R.layout.fragment_country_detail
) {

    override val binding by viewBinding(FragmentCountryDetailBinding::bind)
    override val viewModel by viewModels<CountryDetailViewModel>()
    private val args by navArgs<CountryDetailFragmentArgs>()

    override fun setupRequests() {
        viewModel.getCountryDetail(args.name)
    }

    @SuppressLint("SetTextI18n")
    override fun setupCollects(view: View) = with(binding) {
        viewModel.countryDetailState.collectUIState(
            success = { country ->
                country[0].apply {
                    ivCountryFlag.loadRoundImage(flags.png)
                    tvCountryName.text = name
                    tvRegion.text = getString(R.string.text_region) + " $region"
                    tvCapital.text = getString(R.string.text_capital) + " $capital"
                    tvTimezone.text =
                        getString(R.string.text_timezone) + " ${timezones?.get(0)}"
                    tvCurrency.text =
                        getString(R.string.text_currency) + " ${currencies?.get(0)?.name} " +
                                "[${currencies?.get(0)?.symbol}]"
                }
            }, error = { message ->
                Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
            }
        )
    }
}