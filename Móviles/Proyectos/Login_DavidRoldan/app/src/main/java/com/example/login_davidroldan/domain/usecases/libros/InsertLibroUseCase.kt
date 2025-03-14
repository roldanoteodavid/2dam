package com.example.login_davidroldan.domain.usecases.libros

import com.example.login_davidroldan.data.local.LibroRepository
import com.example.login_davidroldan.domain.modelo.Libro
import javax.inject.Inject

class InsertLibroUseCase @Inject constructor(val libroRepository: LibroRepository) {

    suspend fun invoke(libro: Libro) = libroRepository.insertLibro(libro)
}