package com.example.apollo_davidroldan.domain.usecases

import com.example.apollo_davidroldan.data.repositories.LoginRepository
import com.example.apollo_davidroldan.domain.modelo.Credentials
import javax.inject.Inject


class LoginUseCase @Inject constructor(var repository: LoginRepository){
    suspend operator fun invoke(credentials: Credentials) = repository.login(credentials)
}