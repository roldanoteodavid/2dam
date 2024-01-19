package com.example.examen.domain

import java.util.*

data class Equipo(
    val id: UUID=UUID.randomUUID(),
    var nombre: String,
)
