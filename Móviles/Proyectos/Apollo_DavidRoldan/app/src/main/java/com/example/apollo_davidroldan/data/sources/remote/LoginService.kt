package com.example.apollo_davidroldan.data.sources.remote

import com.example.apollo_davidroldan.data.modelo.LoginTokens
import com.example.apollo_davidroldan.domain.modelo.Credentials
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {
    @POST("/register")
    suspend fun register(@Body credentials: Credentials): Response<Credentials>

    @POST("/login")
    suspend fun login(@Body credentials: Credentials): Response<LoginTokens>

    @GET("/refresh")
    suspend fun refreshToken(@Query("refreshToken") refreshToken: String): Response<Unit>
}