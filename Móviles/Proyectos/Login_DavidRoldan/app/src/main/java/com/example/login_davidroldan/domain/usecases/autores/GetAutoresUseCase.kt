package com.example.login_davidroldan.domain.usecases.autores

import com.example.login_davidroldan.data.local.AutorRepository
import javax.inject.Inject

class GetAutoresUseCase @Inject constructor(val autorRepository: AutorRepository) {
     suspend fun invoke() = autorRepository.getAutores()
}