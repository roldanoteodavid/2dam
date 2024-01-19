package com.example.login_davidroldan.data.local.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.login_davidroldan.common.Constantes

@Entity(tableName = Constantes.AUTOR)
data class AutorEntity(
    @ColumnInfo(name=Constantes.AUTORID)
    @PrimaryKey(autoGenerate = true)
    val authorId: Int,
    @ColumnInfo(name=Constantes.NAME)
    val name: String,
    @ColumnInfo(name=Constantes.BIRTHDATE)
    val birthDate: String
)
