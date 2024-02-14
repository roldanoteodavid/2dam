package com.example.apollo_davidroldan.data.repositories

import com.example.apollo_davidroldan.data.sources.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DirectoresRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getDirectores() =
        withContext(Dispatchers.IO) {
            remoteDataSource.getDirectores()
        }
}