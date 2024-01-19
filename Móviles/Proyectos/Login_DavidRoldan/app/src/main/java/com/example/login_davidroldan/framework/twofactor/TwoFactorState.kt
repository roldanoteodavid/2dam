package com.example.login_davidroldan.framework.twofactor


data class TwoFactorState(
    val error: String? = null,
    val login: Boolean = false,
)