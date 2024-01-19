package com.example.login_davidroldan.data.model

import com.example.login_davidroldan.domain.modelo.Order
import com.example.login_davidroldan.utils.Constants.CUSTOMERID
import com.example.login_davidroldan.utils.Constants.DATE
import com.example.login_davidroldan.utils.Constants.ID
import com.example.login_davidroldan.utils.Constants.TABLEID
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class OrderResponse (
    @SerializedName(ID)
    val id: Int,
    @SerializedName(DATE)
    val date: LocalDateTime,
    @SerializedName(CUSTOMERID)
    val customerid: Int,
    @SerializedName(TABLEID)
    val tableid: Int,
)
fun OrderResponse.toOrder() : Order = Order(id, date, customerid, tableid)
