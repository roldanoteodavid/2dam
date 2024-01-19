package com.example.login_davidroldan.domain.usecases

import com.example.rest_davidroldan.data.repositories.OrderRepository
import com.example.login_davidroldan.domain.modelo.Order
import javax.inject.Inject


class AddOrderUseCase @Inject constructor(var repository: OrderRepository){
    suspend operator fun invoke(order: Order) = repository.addOrder(order)
}