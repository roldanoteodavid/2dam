package com.example.login_davidroldan.domain.modelo

import java.time.LocalDate

data class Customer (
    val id: Int,
    val firstname: String,
    val lastname: String,
    val email: String?,
    val phone: String?,
    val dob: LocalDate?,
    var isSelected: Boolean = false,
)