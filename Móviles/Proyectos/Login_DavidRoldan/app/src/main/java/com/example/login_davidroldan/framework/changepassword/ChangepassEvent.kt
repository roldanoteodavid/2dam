package com.example.login_davidroldan.framework.changepassword

sealed class ChangepassEvent {
    class Changepass(val username: String, val password: String, val temporal: String) : ChangepassEvent()

    object ErrorVisto : ChangepassEvent()
}
