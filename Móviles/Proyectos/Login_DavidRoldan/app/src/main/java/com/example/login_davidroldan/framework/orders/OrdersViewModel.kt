package com.example.login_davidroldan.framework.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_davidroldan.utils.Constants.ORDERDELETED
import com.example.rest_davidroldan.domain.usecases.DeleteOrderUseCase
import com.example.rest_davidroldan.domain.usecases.GetCustomerIdUseCase
import com.example.rest_davidroldan.domain.usecases.GetOrdersUseCase
import com.example.login_davidroldan.utils.NetworkResultt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val getCustomerIdUseCase: GetCustomerIdUseCase,
    private val getOrdersUseCase: GetOrdersUseCase,
    private val deleteOrderUseCase: DeleteOrderUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<OrdersState> by lazy {
        MutableStateFlow(OrdersState())
    }
    val uiState: StateFlow<OrdersState> = _uiState


    init {
        _uiState.value = OrdersState(
            error = null,
        )
    }

    fun handleEvent(event: OrdersEvent) {
        when (event) {
            is OrdersEvent.GetOrders -> {
                getOrders()
            }

            OrdersEvent.ErrorVisto -> _uiState.update { it.copy(error = null) }
            is OrdersEvent.DeleteOrder -> {
                deleteOrder(event.id)
            }

            is OrdersEvent.GetCustomersPorId -> {
                getCustomerId(event.id)
                getOrders(event.id)
            }
        }
    }

    private fun getOrders() {
        viewModelScope.launch {
            val customer = _uiState.value.customer
            if (customer != null) {
                when (val result = getOrdersUseCase(customer.id)) {
                    is NetworkResultt.Success -> {
                        result.data?.let {
                            _uiState.update { ordersState ->  ordersState.copy(orders = it) }
                        }
                    }

                    is NetworkResultt.Error -> {
                        _uiState.update { it.copy(error = result.message) }
                        _uiState.update { it.copy(orders = emptyList())}
                    }
                }

            }
        }
    }

    private fun getOrders(id: Int) {
        viewModelScope.launch {
            when (val result = getOrdersUseCase(id)) {
                is NetworkResultt.Success -> {
                    result.data?.let {
                        _uiState.update { ordersState ->  ordersState.copy(orders = it) }
                    }
                }

                is NetworkResultt.Error -> {
                    _uiState.update { it.copy(error = result.message) }
                }
            }
        }
    }

    private fun deleteOrder(id: Int) {
        viewModelScope.launch {
            when (val result = deleteOrderUseCase(id)) {
                is NetworkResultt.Success -> {
                    _uiState.update { it.copy(error = ORDERDELETED) }
                    getOrders()
                }

                is NetworkResultt.Error -> {
                    _uiState.update { it.copy(error = result.message) }
                }
            }
        }
    }

    private fun getCustomerId(id: Int) {
        viewModelScope.launch {
            when (val result = getCustomerIdUseCase(id)) {
                is NetworkResultt.Success -> {
                    _uiState.update { it.copy(customer = result.data) }
                }

                is NetworkResultt.Error -> {
                    _uiState.update { it.copy(error = result.message) }
                }
            }
        }
    }
}