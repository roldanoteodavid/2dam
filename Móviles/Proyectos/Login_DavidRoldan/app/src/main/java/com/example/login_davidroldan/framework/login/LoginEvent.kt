package com.example.login_davidroldan.framework.login

sealed class LoginEvent {

    class Login(val user: String, val password: String) : LoginEvent()
    class ForgotPassword(val user: String) : LoginEvent()

    object ErrorVisto : LoginEvent()
}
