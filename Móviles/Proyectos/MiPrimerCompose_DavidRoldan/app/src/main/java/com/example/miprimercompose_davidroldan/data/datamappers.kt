package com.example.miprimercompose_davidroldan.data

import com.example.miprimercompose_davidroldan.data.modelo.SimpsonEntity
import com.example.miprimercompose_davidroldan.domain.modelo.Simpson

fun SimpsonEntity.toSimpson() = Simpson(
    this.id,
    this.nombre,
    this.edad,
    this.vivo,
    this.genero,
    this.profesion
)

fun Simpson.toSimpsonEntity() = SimpsonEntity(
    this.id,
    this.nombre,
    this.edad,
    this.vivo,
    this.genero,
    this.profesion
)