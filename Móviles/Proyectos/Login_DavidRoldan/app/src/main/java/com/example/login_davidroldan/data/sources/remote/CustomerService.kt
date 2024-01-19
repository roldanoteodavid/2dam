package com.example.login_davidroldan.data.sources.remote

import com.example.login_davidroldan.data.model.CustomerResponse
import com.example.login_davidroldan.utils.Constants.CUSTOMERID_URL
import com.example.login_davidroldan.utils.Constants.CUSTOMER_URL
import com.example.login_davidroldan.utils.Constants.ID
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface CustomerService {
    @GET(CUSTOMER_URL)
    suspend fun getCustomers(): Response<List<CustomerResponse>>
    @GET(CUSTOMERID_URL)
    suspend fun getCustomer(@Path(ID) id: Int): Response<CustomerResponse>
    @DELETE(CUSTOMERID_URL)
    suspend fun deleteCustomer(@Path(ID) id: Int): Response<CustomerResponse>
}