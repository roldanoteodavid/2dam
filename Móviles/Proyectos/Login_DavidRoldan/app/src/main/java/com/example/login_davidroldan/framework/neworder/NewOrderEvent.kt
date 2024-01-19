package com.example.login_davidroldan.framework.neworder

import com.example.login_davidroldan.domain.modelo.Order;

sealed class NewOrderEvent {

    class AddOrder(val order: Order) : NewOrderEvent()
    class GetId(val id:Int) : NewOrderEvent()
    object ErrorVisto : NewOrderEvent()
}
