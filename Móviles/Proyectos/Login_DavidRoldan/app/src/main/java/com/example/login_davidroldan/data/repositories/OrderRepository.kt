package com.example.rest_davidroldan.data.repositories

import com.example.login_davidroldan.data.sources.remote.RemoteDataSource
import com.example.login_davidroldan.domain.modelo.Order
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@ActivityRetainedScoped
class OrderRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getOrders(id: Int) =
        withContext(Dispatchers.IO)
        {
            remoteDataSource.getOrders(id)
        }

    suspend fun addOrder(order: Order) =
        withContext(Dispatchers.IO)
        {
            remoteDataSource.addOrder(order)
        }

    suspend fun deleteOrder(id: Int) =
        withContext(Dispatchers.IO)
        {
            remoteDataSource.deleteOrder(id)
        }

}




