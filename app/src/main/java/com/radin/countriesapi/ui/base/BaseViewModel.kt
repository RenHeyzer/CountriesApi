package com.radin.countriesapi.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radin.countriesapi.either.Either
import com.renheyzer.common.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
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
        mappedData: ((data: S) -> T)? = null,
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
                            mappedData?.let { map ->
                                state.value = UIState.Success(map(data))
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Collect network request with map model from domain to ui
     */
    protected fun <S, T> collectIdle(
        state: MutableStateFlow<T>,
        request: Flow<S>,
        mappedData: ((data: S) -> T)? = null,
    ) {
        viewModelScope.launch {
            request.collectLatest {
                mappedData?.let { map ->
                    state.value = map(it)
                }
            }
        }
    }
}