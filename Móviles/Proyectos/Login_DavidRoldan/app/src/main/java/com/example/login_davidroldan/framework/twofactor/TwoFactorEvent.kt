package com.example.login_davidroldan.framework.twofactor

sealed class TwoFactorEvent {

    class TwoFactor(val user: String, val twofacode: String) : TwoFactorEvent()

    object ErrorVisto : TwoFactorEvent()
}
