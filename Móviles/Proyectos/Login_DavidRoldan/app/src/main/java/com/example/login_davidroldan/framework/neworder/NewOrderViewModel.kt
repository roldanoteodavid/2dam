package com.example.login_davidroldan.framework.neworder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_davidroldan.utils.Constants.ERRORADDINGORDER
import com.example.login_davidroldan.utils.Constants.ORDERADDED
import com.example.login_davidroldan.domain.modelo.Order
import com.example.login_davidroldan.domain.usecases.AddOrderUseCase
import com.example.login_davidroldan.utils.NetworkResultt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewOrderViewModel @Inject constructor(
    private val addOrderUseCase: AddOrderUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<NewOrderState> by lazy {
        MutableStateFlow(NewOrderState())
    }
    val uiState: StateFlow<NewOrderState> = _uiState


    init {
        _uiState.value = NewOrderState(
            error = null,
        )
    }

    fun handleEvent(event: NewOrderEvent) {
        when (event) {
            is NewOrderEvent.AddOrder -> {
                addOrder(event.order)
            }

            is NewOrderEvent.GetId -> _uiState.update { it.copy(idCustomer = event.id) }
            NewOrderEvent.ErrorVisto -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun addOrder(order: Order) {
        viewModelScope.launch {

            when (addOrderUseCase(order)) {
                is NetworkResultt.Success -> {
                    _uiState.update { it.copy(error = ORDERADDED) }
                }

                is NetworkResultt.Error -> {
                    _uiState.update { it.copy(error = ERRORADDINGORDER) }
                }
            }
        }
    }
}