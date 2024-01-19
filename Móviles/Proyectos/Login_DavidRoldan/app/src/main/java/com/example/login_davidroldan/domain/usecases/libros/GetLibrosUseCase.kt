package com.example.login_davidroldan.domain.usecases.libros

import com.example.login_davidroldan.data.local.LibroRepository
import javax.inject.Inject

class GetLibrosUseCase @Inject constructor(val libroRepository: LibroRepository) {

    suspend fun invoke(id: Int) = libroRepository.getLibros(id)
}