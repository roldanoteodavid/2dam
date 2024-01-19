package com.example.login_davidroldan.data.repositories

import com.example.login_davidroldan.domain.modelo.Credentials
import com.example.login_davidroldan.data.sources.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ActivityRetainedScoped
class LoginRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun register(credentials: Credentials) =
        withContext(Dispatchers.IO) {
            remoteDataSource.register(credentials)
        }

    suspend fun login(credentials: Credentials) =
        withContext(Dispatchers.IO) {
            remoteDataSource.login(credentials)
        }

    suspend fun twofa(credentials: Credentials) =
        withContext(Dispatchers.IO) {
            remoteDataSource.twofa(credentials)
        }

    suspend fun refreshToken(refreshToken: String) =
        withContext(Dispatchers.IO) {
            remoteDataSource.refreshToken(refreshToken)
        }

    suspend fun forgotPassword(username: String) =
        withContext(Dispatchers.IO) {
            remoteDataSource.forgotPassword(username)
        }

    suspend fun changePassword(credentials: Credentials) =
        withContext(Dispatchers.IO) {
            remoteDataSource.changePassword(credentials)
        }
}