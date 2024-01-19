package com.example.login_davidroldan.data.local.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.login_davidroldan.common.Constantes

@Entity( tableName = Constantes.LIBRO,
    foreignKeys = [
        ForeignKey(
            entity = AutorEntity::class,
            parentColumns = [Constantes.AUTORID],
            childColumns = [Constantes.AUTORID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LibroEntity(
    @ColumnInfo(name=Constantes.BOOKID)
    @PrimaryKey(autoGenerate = true)
    val bookId: Int,
    @ColumnInfo(name=Constantes.TITLE)
    val title: String,
    @ColumnInfo(name=Constantes.AUTORID)
    val authorId: Int,
    @ColumnInfo(name=Constantes.PUBLICATIONYEAR)
    val publicationYear: String
)
