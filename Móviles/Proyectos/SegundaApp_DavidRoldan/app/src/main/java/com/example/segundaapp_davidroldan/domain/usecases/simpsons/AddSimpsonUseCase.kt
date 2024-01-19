package com.example.segundaapp_davidroldan.domain.usecases.simpsons

import com.example.segundaapp_davidroldan.data.Repository
import com.example.segundaapp_davidroldan.domain.modelo.Simpson

class AddSimpsonUseCase {
    operator fun invoke(simpson: Simpson) =
        Repository.addSimpson(simpson)
}