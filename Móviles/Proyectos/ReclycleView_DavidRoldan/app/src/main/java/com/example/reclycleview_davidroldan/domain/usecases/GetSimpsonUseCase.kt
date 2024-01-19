package com.example.reclycleview_davidroldan.domain.usecases

import com.example.recyclerview_davidroldan.data.SimpsonRepositoty

class GetSimpsonUseCase {
    operator fun invoke(idSimpson: Int) = SimpsonRepositoty().getPersonaje(idSimpson)
}