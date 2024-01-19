package com.example.reclycleview_davidroldan.data

import com.example.reclycleview_davidroldan.data.model.Simpsonjson
import com.example.reclycleview_davidroldan.domain.modelo.Simpson

fun Simpsonjson.toSimpson() : Simpson = Simpson(id, name, age, vivo, gender, occupation, birthdate)


fun Simpson.toSimpsonjson() : Simpsonjson = Simpsonjson(id,nombre,age, vivo, gender, occupation, birthdate)