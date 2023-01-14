package com.radin.countriesapi.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.radin.countriesapi.ui.state.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Base class for all [Fragment]
 */
abstract class BaseFragment<BaseViewBinding : ViewBinding, ViewModel : BaseViewModel>(
    @LayoutRes layoutId: Int,
) : Fragment(layoutId) {

    protected abstract var binding: BaseViewBinding?
    protected abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupViews()
        setupListeners()
        setupRequests()
        setupCollects()
    }

    open fun initialize() {
    }

    open fun setupViews() {
    }

    open fun setupListeners() {
    }

    open fun setupRequests() {
    }

    open fun setupCollects() {
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

    /**
     * Collect [StateFlow] without states
     */
    protected fun <T> StateFlow<T>.collectIdle(
        state: Lifecycle.State = Lifecycle.State.CREATED,
        action: suspend (T) -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state) {
                this@collectIdle.collect {
                    action(it)
                }
            }
        }
    }
}