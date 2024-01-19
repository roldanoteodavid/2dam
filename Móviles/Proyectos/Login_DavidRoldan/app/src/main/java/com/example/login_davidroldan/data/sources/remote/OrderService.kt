package com.example.login_davidroldan.data.sources.remote

import com.example.login_davidroldan.data.model.OrderResponse
import com.example.login_davidroldan.utils.Constants.CONTENTTYPE
import com.example.login_davidroldan.utils.Constants.ID
import com.example.login_davidroldan.utils.Constants.ORDERID_URL
import com.example.login_davidroldan.utils.Constants.ORDER_URL
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderService {
    @GET(ORDER_URL)
    suspend fun getOrders(): Response<List<OrderResponse>>
    @DELETE(ORDERID_URL)
    suspend fun deleteOrder(@Path(ID) id: Int): Response<Unit>
    @POST(ORDER_URL)
    @Headers(CONTENTTYPE)
    suspend fun addOrder(@Body orderResponse: OrderResponse): Response<Unit>
}