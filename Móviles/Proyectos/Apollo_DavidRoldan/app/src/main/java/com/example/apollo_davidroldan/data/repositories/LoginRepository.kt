package com.example.apollo_davidroldan.data.repositories

import com.example.apollo_davidroldan.data.sources.remote.RemoteDataSource
import com.example.apollo_davidroldan.domain.modelo.Credentials
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

    suspend fun refreshToken(refreshToken: String) =
        withContext(Dispatchers.IO) {
            remoteDataSource.refreshToken(refreshToken)
        }
}