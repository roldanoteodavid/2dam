package com.example.segundaapp_davidroldan.domain.usecases.simpsons

import com.example.reclycleview_davidroldan.data.model.Simpsonjson
import com.example.recyclerview_davidroldan.data.SimpsonRepositoty

class UpdateSimpsonUseCase {
    operator fun invoke(simpson: Simpsonjson, newsimpson: Simpsonjson) =
        SimpsonRepositoty().updateSimpson(simpson, newsimpson)
}