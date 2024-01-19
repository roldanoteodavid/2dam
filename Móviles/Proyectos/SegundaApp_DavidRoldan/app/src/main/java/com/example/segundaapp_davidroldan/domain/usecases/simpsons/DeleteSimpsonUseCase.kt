package com.example.segundaapp_davidroldan.domain.usecases.simpsons

import com.example.segundaapp_davidroldan.data.Repository

class DeleteSimpsonUseCase {
    operator fun invoke(index: Int) =
        Repository.deleteSimpson(index)
}