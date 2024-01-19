package com.example.segundaapp_davidroldan.domain.usecases.simpsons

import com.example.segundaapp_davidroldan.data.Repository

class GetSimpsonUseCase {
    operator fun invoke(id: Int) = Repository.getSimpson(id)
}