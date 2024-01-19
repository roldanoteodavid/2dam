package com.example.login_davidroldan.framework.libros

import com.example.login_davidroldan.domain.modelo.Libro


data class LibrosState(val libros: List<Libro> = emptyList(),
                       val idAutor: Int? = null,
                       val error: String? = null,)