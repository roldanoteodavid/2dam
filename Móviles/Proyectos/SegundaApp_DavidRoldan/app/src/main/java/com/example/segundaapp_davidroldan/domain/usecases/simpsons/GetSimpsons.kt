package com.example.segundaapp_davidroldan.domain.usecases.simpsons

import com.example.segundaapp_davidroldan.data.Repository


class GetSimpsons {
    operator fun invoke() = Repository.getSimpsons()
}