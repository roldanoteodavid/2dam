package com.example.reclycleview_davidroldan.domain.usecases

import com.example.reclycleview_davidroldan.data.model.Simpsonjson
import com.example.recyclerview_davidroldan.data.SimpsonRepositoty

class AddSimpsonUseCase {
    operator fun invoke(simpson: Simpsonjson) =
        SimpsonRepositoty().addSimpson(simpson)
}