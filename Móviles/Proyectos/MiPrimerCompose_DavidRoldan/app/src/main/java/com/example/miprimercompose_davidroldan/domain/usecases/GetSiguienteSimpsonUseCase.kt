package com.example.miprimercompose_davidroldan.domain.usecases

import com.example.miprimercompose_davidroldan.data.SimpsonRepository
import javax.inject.Inject

class GetSiguienteSimpsonUseCase @Inject constructor(private val simpsonRepository: SimpsonRepository) {

         suspend fun invoke(id: Int) = simpsonRepository.getSiguiente(id)
}