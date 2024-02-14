package com.example.apollo_davidroldan.ui.screens.login

sealed class LoginEvent {

    class Login() : LoginEvent()
    class Register() : LoginEvent()

    class onUserNameChange(val username: String) : LoginEvent()
    class onPasswordChange(val password: String) : LoginEvent()

    object ErrorVisto : LoginEvent()
}
