package com.example.login_davidroldan.data.local

import com.example.login_davidroldan.domain.modelo.Libro
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject


@ActivityRetainedScoped
class LibroRepository @Inject constructor(private val libroDao: LibroDao) {

    suspend fun getLibros(id: Int) = libroDao.getLibros(id).map { it.toLibro() }

    suspend fun deleteLibro(libro: Libro) = libroDao.deleteLibro(libro.toLibroEntity())

    suspend fun insertLibro(libro: Libro) = libroDao.insert(libro.toLibroEntity())
}