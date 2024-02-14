package com.example.miprimercompose_davidroldan.data.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.miprimercompose_davidroldan.common.Constantes

@Entity(tableName = Constantes.SIMPSONS)
class SimpsonEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nombre: String = "",
    var edad: Int = 0,
    var vivo: Boolean = false,
    var genero: String = "",
    var profesion: String = ""
)