package com.radin.countriesapi.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.radin.countriesapi.presentation.state.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Base class for all [Fragment]
 */
abstract class BaseFragment<BaseViewBinding : ViewBinding, ViewModel : BaseViewModel>(
    @LayoutRes layoutId: Int,
) : Fragment(layoutId) {

    protected abstract val binding: BaseViewBinding
    protected abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupViews()
        setupListeners()
        setupRequests()
        setupCollects(view)
    }

    open fun initialize() {
    }

    open fun setupViews() {
    }

    open fun setupListeners() {
    }

    open fun setupRequests() {
    }

    open fun setupCollects(view: View) {
    }

    /**
     * Collect [StateFlow] and set all data to states
     */
    protected fun <T> StateFlow<UIState<T>>.collectUIState(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        success: ((data: T) -> Unit)? = null,
        loading: ((text: String) -> Unit)? = null,
        error: ((error: String) -> Unit)? = null,
        beforeUIState: ((state: UIState<T>) -> Unit)? = null,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                collect {
                    beforeUIState?.invoke(it)
                    when (it) {
                        is UIState.Loading -> {
                            loading?.invoke("loading")
                        }
                        is UIState.Error -> {
                            error?.invoke(it.error)
                        }
                        is UIState.Success -> {
                            success?.invoke(it.data)
                        }
                    }
                }
            }
        }
    }
}