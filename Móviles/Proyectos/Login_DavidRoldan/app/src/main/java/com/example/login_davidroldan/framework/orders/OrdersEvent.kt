package com.example.login_davidroldan.framework.orders

sealed class OrdersEvent {

    class DeleteOrder(val id: Int) : OrdersEvent()
    class GetCustomersPorId(val id: Int) : OrdersEvent()
    class GetOrders : OrdersEvent()
    object ErrorVisto : OrdersEvent()
}
