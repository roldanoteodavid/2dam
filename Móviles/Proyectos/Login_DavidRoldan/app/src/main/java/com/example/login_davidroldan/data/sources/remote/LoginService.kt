package com.example.login_davidroldan.data.sources.remote

import com.example.login_davidroldan.domain.modelo.Credentials
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {
    @POST("api/login/register")
    suspend fun register(@Body credentials: Credentials): Response<Credentials>

    @POST("api/login/login")
    suspend fun login(@Body credentials: Credentials): Response<Unit>

    @POST("api/login/twofa")
    suspend fun twofa(@Body credentials: Credentials): Response<Unit>

    @GET("api/login/refresh")
    suspend fun refreshToken(@Query("refreshToken") refreshToken: String): Response<Unit>

    @GET("api/login/forgotpassword")
    suspend fun forgotPassword(@Query("username") username: String): Response<Unit>

    @POST("api/login/changepassword")
    suspend fun changePassword(@Body credentials: Credentials): Response<Unit>




}