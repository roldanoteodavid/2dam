package com.example.login_davidroldan.data.sources.remote

import com.example.login_davidroldan.common.Constantes
import com.example.login_davidroldan.data.model.toCustomer
import com.example.login_davidroldan.data.model.toOrder
import com.example.login_davidroldan.domain.modelo.Credentials
import com.example.login_davidroldan.domain.modelo.Customer
import com.example.login_davidroldan.domain.modelo.Order
import com.example.login_davidroldan.domain.modelo.toOrderResponse
import com.example.login_davidroldan.utils.Constants
import com.example.login_davidroldan.utils.Constants.ERROR
import com.example.login_davidroldan.utils.NetworkResultt
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val customerService: CustomerService,
    private val orderService: OrderService,
    private val loginService: LoginService,
    private val tokenManager: TokenManager
) {

    suspend fun getCustomers(): NetworkResultt<List<Customer>> {
        try {
            val response = customerService.getCustomers()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResultt.Success(body.map { it.toCustomer() })
                }
            } else {
                return NetworkResultt.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            return NetworkResultt.Error(e.message ?: e.toString())
        }
        return NetworkResultt.Error(ERROR)
    }

    suspend fun getCustomer(id: Int): NetworkResultt<Customer> {
        try {
            val response = customerService.getCustomer(id)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResultt.Success(body.toCustomer())
                }
            } else {
                return NetworkResultt.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            return NetworkResultt.Error(e.message ?: e.toString())
        }
        return NetworkResultt.Error(ERROR)
    }

    suspend fun deleteCustomer(id: Int): NetworkResultt<Unit> {
        return try {
            val response = customerService.deleteCustomer(id)

            val result = if (response.isSuccessful) {
                NetworkResultt.Success(Unit)
            } else {
                if (response.code() == 403) {
                    NetworkResultt.Error("${response.code()} ${response.message()}" + Constants.NO_PERMISION)
                } else if (response.code() == 401) {
                    NetworkResultt.Error("${response.code()} ${response.message()}" + Constants.HASORDERS)
                } else {
                    NetworkResultt.Error("${response.code()} ${response.message()}")
                }
            }

            result
        } catch (e: Exception) {
            NetworkResultt.Error(e.message ?: e.toString())
        }
    }

    suspend fun getOrders(customerid: Int): NetworkResultt<List<Order>> {
        try {
            val response = orderService.getOrders()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    val filteredOrders =
                        body.filter { it.customerid == customerid }.map { it.toOrder() }
                    return NetworkResultt.Success(filteredOrders)
                }
            } else {
                return NetworkResultt.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            return NetworkResultt.Error(e.message ?: e.toString())
        }
        return NetworkResultt.Error(ERROR)
    }

    suspend fun addOrder(order: Order): NetworkResultt<Unit> {
        return try {
            val response = orderService.addOrder(order.toOrderResponse())

            if (response.isSuccessful) {
                NetworkResultt.Success(Unit)
            } else {
                if (response.code() == 403) {
                    NetworkResultt.Error("${response.code()} ${response.message()}" + Constants.NO_PERMISION)
                } else {
                    NetworkResultt.Error("${response.code()} ${response.message()}")
                }
            }
        } catch (e: Exception) {
            NetworkResultt.Error(e.message ?: e.toString())
        }
    }

    suspend fun deleteOrder(id: Int): NetworkResultt<Unit> {
        return try {
            val response = orderService.deleteOrder(id)

            if (response.isSuccessful) {
                NetworkResultt.Success(Unit)
            } else {
                if (response.code() == 403) {
                    NetworkResultt.Error("${response.code()} ${response.message()}" + Constants.NO_PERMISION)
                } else {
                    NetworkResultt.Error("${response.code()} ${response.message()}")
                }
            }
        } catch (e: Exception) {
            NetworkResultt.Error(e.message ?: e.toString())
        }
    }

    suspend fun register(credentials: Credentials): NetworkResultt<Unit> {
        return try {
            val response = loginService.register(credentials)
            if (response.isSuccessful) {
                NetworkResultt.Success(Unit)
            } else {
                if (response.code() == 401) {
                    NetworkResultt.Error(Constantes.USER_EXISTS)
                } else {
                    NetworkResultt.Error("${response.code()} ${response.message()}")
                }
            }
        } catch (e: Exception) {
            NetworkResultt.Error(e.message ?: e.toString())
        }
    }

    suspend fun login(credentials: Credentials): NetworkResultt<Unit> {
        return try {
            val response = loginService.login(credentials)
            if (response.isSuccessful) {
                NetworkResultt.Success(Unit)
            } else {
                if (response.code() == 401) {
                    NetworkResultt.Error(Constantes.USERORPASS_INCORRECT)
                }else if (response.code() == 500) {
                    NetworkResultt.Error(Constantes.USER_NOT_ACTIVE)
                } else {
                    NetworkResultt.Error("${response.code()} ${response.message()}")
                }
            }
        } catch (e: Exception) {
            NetworkResultt.Error(e.message ?: e.toString())
        }
    }

    suspend fun twofa(credentials: Credentials): NetworkResultt<Unit> {
        return try {
            val response = loginService.twofa(credentials)
            if (response.isSuccessful) {
                val accessToken = response.headers()[Constantes.AUTHORIZATION]
                val refreshToken = response.headers()[Constantes.REFRESH_TOKEN]

                accessToken?.let {
                    tokenManager.saveAccessToken(it)
                }
                refreshToken?.let {
                    tokenManager.saveToken(it)
                }

                NetworkResultt.Success(Unit)
            } else {
                if (response.code() == 401) {
                    NetworkResultt.Error(Constantes.INCORRECT_TWOFA)
                }else {
                    NetworkResultt.Error("${response.code()} ${response.message()}")
                }
            }
        } catch (e: Exception) {
            NetworkResultt.Error(e.message ?: e.toString())
        }
    }

    suspend fun refreshToken(refreshToken: String): NetworkResultt<Unit> {
        return try {
            val response = loginService.refreshToken(refreshToken)
            if (response.isSuccessful) {
                NetworkResultt.Success(Unit)
            } else {
                NetworkResultt.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResultt.Error(e.message ?: e.toString())
        }
    }

    suspend fun forgotPassword(username: String): NetworkResultt<Unit> {
        return try {
            val response = loginService.forgotPassword(username)
            if (response.isSuccessful) {
                NetworkResultt.Success(Unit)
            } else {
                NetworkResultt.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResultt.Error(e.message ?: e.toString())
        }
    }

    suspend fun changePassword(credentials: Credentials): NetworkResultt<Unit> {
        return try {
            val response = loginService.changePassword(credentials)
            if (response.isSuccessful) {
                NetworkResultt.Success(Unit)
            } else {
                NetworkResultt.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResultt.Error(e.message ?: e.toString())
        }
    }
}

