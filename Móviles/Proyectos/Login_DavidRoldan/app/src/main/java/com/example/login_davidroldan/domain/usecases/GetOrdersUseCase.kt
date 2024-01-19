package com.example.rest_davidroldan.domain.usecases

import com.example.rest_davidroldan.data.repositories.OrderRepository
import javax.inject.Inject


class GetOrdersUseCase @Inject constructor(var repository: OrderRepository){
    suspend operator fun invoke(id:Int) = repository.getOrders(id)
}