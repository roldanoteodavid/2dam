package com.example.login_davidroldan.framework.neworder

data class NewOrderState(
    val idCustomer: Int = 0,
    val error: String? = null,
    val added: Boolean = false,
)