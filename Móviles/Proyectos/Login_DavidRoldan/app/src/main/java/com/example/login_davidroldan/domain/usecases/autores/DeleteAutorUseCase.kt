package com.example.login_davidroldan.domain.usecases.autores

import com.example.login_davidroldan.data.local.AutorRepository
import com.example.login_davidroldan.domain.modelo.Autor
import javax.inject.Inject

class DeleteAutorUseCase @Inject constructor(val autorRepository: AutorRepository) {

     suspend fun invoke(autor: Autor) = autorRepository.deleteAutor(autor)
}