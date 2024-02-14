package com.example.apollo_davidroldan.data.sources.remote

import com.apollographql.apollo3.ApolloClient
import com.example.apollo_davidroldan.common.Constantes
import com.example.apollo_davidroldan.domain.modelo.Credentials
import com.example.apollo_davidroldan.domain.modelo.Director
import com.example.apollo_davidroldan.utils.NetworkResult
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val loginService: LoginService,
    private val tokenManager: TokenManager,
    private val apolloClient: ApolloClient,
) {

    suspend fun register(credentials: Credentials): NetworkResult<Unit> {
        return try {
            val response = loginService.register(credentials)
            if (response.isSuccessful) {
                NetworkResult.Success(Unit)
            } else {
                if (response.code() == 400) {
                    NetworkResult.Error(Constantes.USER_EXISTS)
                } else {
                    NetworkResult.Error("${response.code()} ${response.message()}")
                }
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: e.toString())
        }
    }

    suspend fun login(credentials: Credentials): NetworkResult<Unit> {
        return try {
            val response = loginService.login(credentials)
            if (response.isSuccessful) {
                val loginTokens = response.body()
                if (loginTokens != null) {
                    loginTokens.accessToken?.let { tokenManager.saveAccessToken(it) }
                    loginTokens.refreshToken?.let { tokenManager.saveRefreshToken(it) }
                    NetworkResult.Success(Unit)
                } else {
                    NetworkResult.Error(Constantes.ERROR_DESCONOCIDO)
                }
            } else {
                if (response.code() == 400) {
                    NetworkResult.Error(Constantes.USERORPASS_INCORRECT)
                } else {
                    NetworkResult.Error("${response.code()} ${response.message()}")
                }
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: e.toString())
        }
    }

    suspend fun refreshToken(refreshToken: String): NetworkResult<Unit> {
        return try {
            val response = loginService.refreshToken(refreshToken)
            if (response.isSuccessful) {
                NetworkResult.Success(Unit)
            } else {
                NetworkResult.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: e.toString())
        }
    }

    suspend fun getDirectores(): NetworkResult<List<Director>> {
        return try {
            val response = apolloClient.query(DirectoresQuery()).execute()
            if (response.isSuccessful) {
                val directores = response.body()
                if (directores != null) {
                    NetworkResult.Success(directores)
                } else {
                    NetworkResult.Error(Constantes.ERROR_DESCONOCIDO)
                }
            } else {
                NetworkResult.Error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: e.toString())
        }
    }
}

