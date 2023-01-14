package com.radin.countriesapi.di

import com.radin.countriesapi.data.repositories.CountriesRepositoryImpl
import com.radin.countriesapi.domain.repositories.CountriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindCountriesRepository(repositoryImpl: CountriesRepositoryImpl): CountriesRepository
}