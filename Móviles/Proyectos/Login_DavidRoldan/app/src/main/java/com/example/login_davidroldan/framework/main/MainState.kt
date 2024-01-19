package com.example.login_davidroldan.framework.main

import com.example.login_davidroldan.domain.modelo.Customer


data class MainState(
    val customers: List<Customer> = emptyList(),
    val customersSelected: List<Customer> = emptyList(),
    val selectMode: Boolean = false,
    val error: String? = null,
)