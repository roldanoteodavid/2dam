package com.example.login_davidroldan.data.local

import com.example.login_davidroldan.data.local.modelo.AutorEntity
import com.example.login_davidroldan.data.local.modelo.LibroEntity
import com.example.login_davidroldan.domain.modelo.Autor
import com.example.login_davidroldan.domain.modelo.Libro

fun AutorEntity.toAutor() = Autor(
    this.authorId,
    this.name,
    this.birthDate
)

fun Autor.toAutorEntity() = AutorEntity(
    this.authorId,
    this.name,
    this.birthDate
)

fun LibroEntity.toLibro() = Libro(
    this.bookId,
    this.title,
    this.authorId,
    this.publicationYear
)

fun Libro.toLibroEntity() = LibroEntity(
    this.bookId,
    this.title,
    this.authorId,
    this.publicationYear
)