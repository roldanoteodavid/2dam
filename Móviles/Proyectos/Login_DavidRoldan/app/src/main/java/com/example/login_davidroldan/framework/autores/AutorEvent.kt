package com.example.login_davidroldan.framework.autores

import com.example.login_davidroldan.domain.modelo.Autor

sealed class AutorEvent {

    class DeleteAutoresSeleccionados() : AutorEvent()
    class DeleteAutor(val autor: Autor) : AutorEvent()
    class AddAutor() : AutorEvent()
    class SeleccionaAutor(val autor: Autor) : AutorEvent()

    object GetAutores : AutorEvent()
    object ErrorVisto : AutorEvent()

    object StartSelectMode: AutorEvent()
    object ResetSelectMode: AutorEvent()
}
