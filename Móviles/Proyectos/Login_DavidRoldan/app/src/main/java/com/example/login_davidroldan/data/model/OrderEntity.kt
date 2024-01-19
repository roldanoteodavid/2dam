package com.example.login_davidroldan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.login_davidroldan.common.Constantes
import com.example.login_davidroldan.domain.modelo.Order
import java.time.LocalDateTime

@Entity(tableName = Constantes.ORDERS)
data class OrderEntity(
    @PrimaryKey
    val id: Int,
    val date: LocalDateTime,
    val customerId: Int,
    val tableId: Int,
)

fun OrderEntity.toOrderResponse() : OrderResponse = OrderResponse(id, date, customerId, tableId)

fun Order.toOrderEntity() : OrderEntity = OrderEntity(id, date, customerId, tableId)