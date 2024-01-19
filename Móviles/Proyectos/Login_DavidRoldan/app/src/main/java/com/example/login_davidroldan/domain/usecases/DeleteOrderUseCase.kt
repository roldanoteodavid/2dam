package com.example.rest_davidroldan.domain.usecases

import com.example.rest_davidroldan.data.repositories.OrderRepository
import javax.inject.Inject


class DeleteOrderUseCase @Inject constructor(var repository: OrderRepository){
    suspend operator fun invoke(int: Int) = repository.deleteOrder(int)
}