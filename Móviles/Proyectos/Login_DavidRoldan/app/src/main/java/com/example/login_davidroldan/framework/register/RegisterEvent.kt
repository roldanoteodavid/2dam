package com.example.login_davidroldan.framework.register

sealed class RegisterEvent {
    class Register(val user: String, val email: String,val password: String) : RegisterEvent()

    object ErrorVisto : RegisterEvent()
}
