package com.example.login_davidroldan.domain.modelo

data class Credentials (
    val username: String,
    val email: String,
    val password: String,
    val temporalCode: String,
)