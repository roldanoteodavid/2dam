package com.example.login_davidroldan.framework.main

import com.example.login_davidroldan.domain.modelo.Customer

sealed class MainEvent {

    class DeleteCustomersSeleccionadas() : MainEvent()
    class DeleteCustomer(val customer: Customer) : MainEvent()
    class SeleccionaCustomers(val customer: Customer) : MainEvent()

    class GetCustomersFiltradas(val filtro: String) : MainEvent()
    object GetCustomers : MainEvent()
    object ErrorVisto : MainEvent()

    object StartSelectMode: MainEvent()
    object ResetSelectMode: MainEvent()
}
