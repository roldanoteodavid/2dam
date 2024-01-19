package com.example.login_davidroldan.domain.usecases

import com.example.login_davidroldan.data.repositories.CustomerRepository
import javax.inject.Inject


class GetCustomersUseCase @Inject constructor(var repository: CustomerRepository){
    suspend operator fun invoke() = repository.getCustomers()
}