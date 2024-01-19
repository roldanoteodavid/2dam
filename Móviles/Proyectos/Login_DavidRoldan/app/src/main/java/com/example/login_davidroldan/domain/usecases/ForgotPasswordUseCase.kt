package com.example.login_davidroldan.domain.usecases

import com.example.login_davidroldan.data.repositories.LoginRepository
import javax.inject.Inject


class ForgotPasswordUseCase @Inject constructor(var repository: LoginRepository){
    suspend operator fun invoke(username: String) = repository.forgotPassword(username)
}