package com.example.login_davidroldan.domain.usecases

import com.example.login_davidroldan.data.repositories.LoginRepository
import com.example.login_davidroldan.domain.modelo.Credentials
import javax.inject.Inject


class RegisterUseCase @Inject constructor(var repository: LoginRepository){
    suspend operator fun invoke(credentials: Credentials) = repository.register(credentials)
}