package com.example.login_davidroldan.framework.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_davidroldan.domain.usecases.DeleteCustomerUseCase
import com.example.login_davidroldan.utils.Constants.CUSTOMERDELETED
import com.example.login_davidroldan.utils.Constants.OF
import com.example.login_davidroldan.utils.Constants.TOTALDELETED
import com.example.login_davidroldan.domain.modelo.Customer
import com.example.login_davidroldan.domain.usecases.GetCustomersCatchedUseCase
import com.example.login_davidroldan.utils.Utils
import com.example.login_davidroldan.domain.usecases.GetCustomersUseCase
import com.example.login_davidroldan.utils.Constants
import com.example.login_davidroldan.utils.NetworkResultt
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val getCustomersUseCase: GetCustomersUseCase,
    private val deleteCustomerUseCase: DeleteCustomerUseCase,
    private val getCustomersCatchedUseCase: GetCustomersCatchedUseCase
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private var selectedCustomers = mutableListOf<Customer>()


    private val _uiState: MutableStateFlow<MainState> by lazy {
        MutableStateFlow(MainState())
    }
    val uiState: StateFlow<MainState> = _uiState


    init {
        _uiState.value = MainState(
            error = null,
            customersSelected = selectedCustomers.toList(),
            selectMode = false
        )
        getCustomers()
    }

    fun handleEvent(event: MainEvent) {
        when (event) {
            MainEvent.GetCustomers -> {
                getCustomers()
            }

            MainEvent.ErrorVisto -> _uiState.update { it.copy(error = null) }

            is MainEvent.DeleteCustomer -> {
                deleteCustomer(event.customer)
            }

            is MainEvent.SeleccionaCustomers -> seleccionaCustomer(event.customer)
            is MainEvent.DeleteCustomersSeleccionadas -> {
                deleteCustomers()
            }

            is MainEvent.GetCustomersFiltradas -> getCustomers(event.filtro)
            MainEvent.ResetSelectMode -> {
                resetSelectMode()
            }

            MainEvent.StartSelectMode -> _uiState.update { it.copy(selectMode = true) }
        }
    }

    private fun resetSelectMode() {
        _uiState.update { it.copy(selectMode = false, customersSelected = selectedCustomers) }
        selectedCustomers.clear()

    }

    private fun getCustomers() {
        viewModelScope.launch {
            if (Utils.hasInternetConnection(context = appContext)) {
                val result = getCustomersUseCase()
                when (result) {
                    is NetworkResultt.Error -> _error.value = result.message ?: ""
                    is NetworkResultt.Success -> {
                        result.data?.let { customers ->
                            _uiState.update { it.copy(customers = customers) }
                        }
                    }
                }
            } else {
                _uiState.update {
                    it.copy(
                        error = Constants.NO_HAY_INTERNET,
                    )
                }
                when(val result = getCustomersCatchedUseCase.invoke()){
                    is NetworkResultt.Error -> _uiState.update { it.copy(error = result.message) }
                    is NetworkResultt.Success -> {
                        result.data?.let { customers ->
                            _uiState.update { it.copy(customers = customers) }
                        }
                    }
                }
            }

        }

    }

    private fun getCustomers(filtro: String) {
        viewModelScope.launch {
            if (Utils.hasInternetConnection(context = appContext)){
                when (val result = getCustomersUseCase()) {
                    is NetworkResultt.Error -> _error.value = result.message ?: ""
                    is NetworkResultt.Success -> {
                        result.data?.let { customers ->
                            _uiState.update {
                                it.copy(customers = customers.filter {
                                    it.firstname.contains(
                                        filtro,
                                        ignoreCase = true
                                    )
                                })
                            }
                        }
                    }
                }
            } else {
                when (val result = getCustomersCatchedUseCase()) {
                    is NetworkResultt.Error -> _error.value = result.message ?: ""
                    is NetworkResultt.Success -> {
                        result.data?.let { customers ->
                            _uiState.update {
                                it.copy(customers = customers.filter {
                                    it.firstname.contains(
                                        filtro,
                                        ignoreCase = true
                                    )
                                })
                            }
                        }
                    }
                }
            }
        }
    }


    private fun deleteCustomers() {
        viewModelScope.launch {
            val totalselected = selectedCustomers.size
            var totaldeleted = 0
            for (customer in selectedCustomers) {
                val result = deleteCustomerUseCase(customer.id)
                when (result) {
                    is NetworkResultt.Success -> {
                        totaldeleted++
                    }

                    is NetworkResultt.Error -> {
                        _uiState.update { it.copy(error = result.message) }
                    }
                }
            }
            _uiState.update { it.copy(error = TOTALDELETED + "$totaldeleted" + OF + "$totalselected") }
            selectedCustomers.clear()
            _uiState.update { it.copy(customersSelected = selectedCustomers.toList()) }
            getCustomers()

        }

    }

    private fun deleteCustomer(customer: Customer) {
        viewModelScope.launch {
            val result = deleteCustomerUseCase(customer.id)
            when (result) {
                is NetworkResultt.Success -> {
                    _uiState.update { it.copy(error = CUSTOMERDELETED) }
                    getCustomers()
                }

                is NetworkResultt.Error -> {
                    _uiState.update { it.copy(error = result.message) }
                    getCustomers()
                }
            }
        }
    }


    private fun seleccionaCustomer(customer: Customer) {
        if (isSelected(customer)) {
            selectedCustomers.remove(customer)
            if (selectedCustomers.isEmpty()) {
                _uiState.update { it.copy(selectMode = false) }
            }
        } else {
            selectedCustomers.add(customer)
        }
        _uiState.update { it.copy(customersSelected = selectedCustomers) }
    }

    private fun isSelected(customer: Customer): Boolean {
        return selectedCustomers.contains(customer)
    }


}