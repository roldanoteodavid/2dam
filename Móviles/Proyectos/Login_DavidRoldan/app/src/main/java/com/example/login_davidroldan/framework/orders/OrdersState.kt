package com.example.login_davidroldan.framework.orders

import com.example.login_davidroldan.domain.modelo.Customer
import com.example.login_davidroldan.domain.modelo.Order


data class OrdersState(val orders: List<Order> = emptyList(),
                       val customer: Customer? = null,
                       val error: String? = null,)