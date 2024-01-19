package com.example.login_davidroldan.data.local

import com.example.login_davidroldan.domain.modelo.Autor
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
class AutorRepository @Inject constructor(private val autorDao: AutorDao) {

    suspend fun getAutores() = autorDao.getAutores().map { it.toAutor() }

    suspend fun deleteAutor(autor: Autor) = autorDao.deleteAutor(autor.toAutorEntity())

    suspend fun addAutor(autor: Autor) = autorDao.addAutor(autor.toAutorEntity())
}