package com.radin.countriesapi.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radin.countriesapi.domain.either.Either
import com.radin.countriesapi.presentation.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> mutableStateFlow(): MutableStateFlow<UIState<T>> =
        MutableStateFlow(UIState.Loading())

    /**
     * Collect network request with states and map model from domain to ui
     */
    protected fun <S, T> collectState(
        state: MutableStateFlow<UIState<T>>,
        request: Flow<Either<String, S>>,
        mapping: ((data: S) -> T)? = null
    ) {
        viewModelScope.launch {
            request.collect {
                when (it) {
                    is Either.Left -> {
                        it.message?.let { error ->
                            state.value = UIState.Error(error)
                        }
                    }
                    is Either.Right -> {
                        it.data?.let { data ->
                            mapping?.let { map ->
                                state.value = UIState.Success(map(data))
                            }
                        }
                    }
                }
            }
        }
    }
}