package com.radin.countriesapi.ui.fragments.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.radin.countriesapi.R
import com.radin.countriesapi.databinding.FragmentListOfCountiresBinding
import com.radin.countriesapi.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfCountriesFragment :
    BaseFragment<FragmentListOfCountiresBinding, ListOfCountriesViewModel>(
        R.layout.fragment_list_of_countires
    ) {
    override var binding: FragmentListOfCountiresBinding? = null
    override val viewModel by viewModels<ListOfCountriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListOfCountiresBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}