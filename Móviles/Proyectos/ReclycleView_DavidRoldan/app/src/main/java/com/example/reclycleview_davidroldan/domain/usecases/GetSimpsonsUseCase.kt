package com.example.reclycleview_davidroldan.domain.usecases

import com.example.recyclerview_davidroldan.data.SimpsonRepositoty

class GetSimpsonsUseCase (var repository: SimpsonRepositoty){
    operator fun invoke() = repository.getLista()
}