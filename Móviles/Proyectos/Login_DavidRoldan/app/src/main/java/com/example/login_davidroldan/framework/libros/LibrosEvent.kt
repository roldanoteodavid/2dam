package com.example.login_davidroldan.framework.libros

import com.example.login_davidroldan.domain.modelo.Libro

sealed class LibrosEvent {

    class DeleteLibro(val libro: Libro) : LibrosEvent()
    class GetLibros(val id: Int) : LibrosEvent()
    class AddLibro() : LibrosEvent()
    object ErrorVisto : LibrosEvent()
}
