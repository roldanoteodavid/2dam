package com.example.login_davidroldan.domain.usecases

import com.example.login_davidroldan.data.repositories.CustomerRepository
import javax.inject.Inject


class DeleteCustomerUseCase @Inject constructor(var repository: CustomerRepository){
    suspend operator fun invoke(int: Int) = repository.deleteCustomers(int)
}