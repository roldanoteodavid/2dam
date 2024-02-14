package com.example.apollo_davidroldan.domain.modelo

import java.util.Date

data class Director(
    val id: Int,
    val nombre: String,
    val nacionalidad: String,
    val fechaNacimiento: Date,
)