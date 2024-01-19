package com.example.login_davidroldan.domain.modelo

import com.example.login_davidroldan.data.model.OrderResponse
import java.time.LocalDateTime

data class Order(
    val id: Int,
    val date: LocalDateTime,
    val customerId: Int,
    val tableId: Int,
)
fun Order.toOrderResponse() : OrderResponse = OrderResponse(id, date, customerId, tableId)
