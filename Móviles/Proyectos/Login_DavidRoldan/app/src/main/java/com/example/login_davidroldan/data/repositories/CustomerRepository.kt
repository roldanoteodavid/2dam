package com.example.login_davidroldan.data.repositories

import com.example.login_davidroldan.common.Constantes
import com.example.login_davidroldan.data.dao.CustomerDao
import com.example.login_davidroldan.data.model.toCustomer
import com.example.login_davidroldan.data.model.toCustomerEntity
import com.example.login_davidroldan.data.sources.remote.RemoteDataSource
import com.example.login_davidroldan.domain.modelo.Customer
import com.example.login_davidroldan.utils.NetworkResultt
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@ActivityRetainedScoped
class CustomerRepository @Inject constructor(
    private val customerDao: CustomerDao,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getCustomers(): NetworkResultt<List<Customer>> {
        return withContext(Dispatchers.IO) {
            try {

                // Luego obtenemos los nuevos datos desde el origen remoto
                val remoteResult = remoteDataSource.getCustomers()

                if (remoteResult is NetworkResultt.Success) {
                    // Insertamos los nuevos datos en la base de datos local
                    remoteResult.data?.let { customers ->
                        customerDao.deleteAll(customers.map { it.toCustomerEntity() })
                        customerDao.insertAll(customers.map { it.toCustomerEntity() })
                    }
                }

                return@withContext remoteResult
            } catch (e: Exception) {
                return@withContext NetworkResultt.Error(e.message ?: Constantes.ERROR_DESCONOCIDO)
            }
        }
    }

    suspend fun getCustomer(id: Int) =
        withContext(Dispatchers.IO)
        {
            remoteDataSource.getCustomer(id)
        }
    suspend fun deleteCustomers(id:Int) =
        withContext(Dispatchers.IO)
        { remoteDataSource.deleteCustomer(id) }

    suspend fun getCustomersCatched(): NetworkResultt<List<Customer>> {
        return withContext(Dispatchers.IO) {
            return@withContext customerDao.getAll().let { list ->
                NetworkResultt.Success(list.map { it.toCustomer() })
            }
        }
    }
}




