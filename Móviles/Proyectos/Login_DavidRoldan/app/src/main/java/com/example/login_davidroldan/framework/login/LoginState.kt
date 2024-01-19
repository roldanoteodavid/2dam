package com.example.login_davidroldan.framework.login


data class LoginState(
    val error: String? = null,
    val login: Boolean = false,
    val forgotPassword: Boolean = false,
)