package com.example.login_davidroldan.framework.libros

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_davidroldan.domain.modelo.Libro
import com.example.login_davidroldan.domain.usecases.libros.DeleteLibroUseCase
import com.example.login_davidroldan.domain.usecases.libros.GetLibrosUseCase
import com.example.login_davidroldan.domain.usecases.libros.InsertLibroUseCase
import com.example.login_davidroldan.utils.Constants
import com.github.javafaker.Faker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibrosViewModel @Inject constructor(
    private val getLibrosUseCase: GetLibrosUseCase,
    private val insertLibroUseCase: InsertLibroUseCase,
    private val deleteLibroUseCase: DeleteLibroUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<LibrosState> by lazy {
        MutableStateFlow(LibrosState())
    }
    val uiState: StateFlow<LibrosState> = _uiState


    init {
        _uiState.value = LibrosState(
            error = null,
        )
    }

    fun handleEvent(event: LibrosEvent) {
        when (event) {
            is LibrosEvent.GetLibros -> {
                getLibros(event.id)
            }

            LibrosEvent.ErrorVisto -> _uiState.update { it.copy(error = null) }
            is LibrosEvent.DeleteLibro -> {
                deleteLibro(event.libro)
            }

            is LibrosEvent.AddLibro -> {
                addLibro()
            }
        }
    }


    private fun getLibros(id: Int) {
        viewModelScope.launch {
            try {
                val result = getLibrosUseCase.invoke(id)
                _uiState.update { it.copy(libros = result) }
                _uiState.update { it.copy(idAutor = id) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun deleteLibro(libro: Libro) {
        viewModelScope.launch {
            deleteLibroUseCase.invoke(libro)
            getLibros(libro.authorId)
        }
    }

    private fun generateRandomBookTitle(): String {
        val faker = Faker()
        return faker.book().title()
    }

    private fun generateRandomYear(): String {
        // Genera un año aleatorio entre 1900 y 2024
        val randomYear = (1900..2024).random()
        return randomYear.toString()
    }

    private fun addLibro() {
        viewModelScope.launch {
            try {
                val idAutor = uiState.value.idAutor
                if (idAutor == null) {
                    _uiState.update { it.copy(error = Constants.NO_AUTOR_SELECCIONADO) }
                }else{
                    val libro = Libro(0, generateRandomBookTitle(), idAutor, generateRandomYear())
                    insertLibroUseCase.invoke(libro)
                    _uiState.update { it.copy(error = Constants.LIBRO_ANYADIDO) }
                    getLibros(idAutor)
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
}