package com.example.examen.domain

import java.time.LocalDate
import java.util.*

data class Jugador(
    val id: UUID = UUID.randomUUID(),
    val nombre: String,
    val posicion: String,
    val idEquipo: UUID,
    val fechaContratacion: LocalDate,
    val actuacionesPartidos: List<String>? = null,
    val equiposAnteriores: List<String>? = null,
)
