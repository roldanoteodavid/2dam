package com.example.miprimercompose_davidroldan.domain.usecases

import com.example.miprimercompose_davidroldan.data.SimpsonRepository
import com.example.miprimercompose_davidroldan.domain.modelo.Simpson
import javax.inject.Inject

class UpdateSimpsonUseCase @Inject constructor(private val simpsonRepository: SimpsonRepository) {

         suspend fun invoke(simpson: Simpson) = simpsonRepository.updateSimpson(simpson)
}