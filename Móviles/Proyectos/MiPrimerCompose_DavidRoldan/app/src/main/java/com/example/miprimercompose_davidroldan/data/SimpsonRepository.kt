package com.example.miprimercompose_davidroldan.data

import com.example.miprimercompose_davidroldan.domain.modelo.Simpson
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class SimpsonRepository @Inject constructor(private val simpsonDao: SimpsonDao) {
    suspend fun getSimpsons() = simpsonDao.getSimpsons().map { it.toSimpson() }

    suspend fun deleteSimpson(simpson: Simpson) =
        simpsonDao.deleteSimpson(simpson.toSimpsonEntity())

    suspend fun insertSimpson(simpson: Simpson): Simpson {
        var simpsonEntity = simpson.toSimpsonEntity()
        simpsonEntity.id = 0
        val insertedId = simpsonDao.addSimpson(simpsonEntity)
        return simpson.copy(id = insertedId.toInt())
    }

    suspend fun updateSimpson(simpson: Simpson) =
        simpsonDao.updateSimpson(simpson.toSimpsonEntity())

    suspend fun getSiguiente(id: Int) = simpsonDao.getSiguiente(id)?.toSimpson()

    suspend fun getAnterior(id: Int) = simpsonDao.getAnterior(id)?.toSimpson()
}