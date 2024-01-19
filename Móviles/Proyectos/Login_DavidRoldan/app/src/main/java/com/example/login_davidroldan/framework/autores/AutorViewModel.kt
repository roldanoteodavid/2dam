package com.example.login_davidroldan.framework.autores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_davidroldan.domain.modelo.Autor
import com.example.login_davidroldan.domain.usecases.autores.DeleteAutorUseCase
import com.example.login_davidroldan.domain.usecases.autores.GetAutoresUseCase
import com.example.login_davidroldan.domain.usecases.autores.InsertAutorUseCase
import com.example.login_davidroldan.utils.Constants
import com.example.login_davidroldan.utils.Constants.TOTALDELETED
import com.github.javafaker.Faker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class AutorViewModel @Inject constructor(
    private val getAutoresUseCase: GetAutoresUseCase,
    private val deleteAutorUseCase: DeleteAutorUseCase,
    private val insertAutorUseCase: InsertAutorUseCase,
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private var selectedAutores = mutableListOf<Autor>()


    private val _uiState: MutableStateFlow<AutorState> by lazy {
        MutableStateFlow(AutorState())
    }
    val uiState: StateFlow<AutorState> = _uiState


    init {
        _uiState.value = AutorState(
            error = null,

            autoresSelected = selectedAutores.toList(),
            selectMode = false
        )
        getAutores()
    }

    fun handleEvent(event: AutorEvent) {
        when (event) {
            AutorEvent.GetAutores -> {
                getAutores()
            }

            AutorEvent.ErrorVisto -> _uiState.update { it.copy(error = null) }

            is AutorEvent.DeleteAutor -> {
                deleteAutor(event.autor)
            }
            is AutorEvent.AddAutor -> {
                addAutor()
            }

            is AutorEvent.SeleccionaAutor -> seleccionaAutor(event.autor)
            is AutorEvent.DeleteAutoresSeleccionados -> {
                deleteAutors()
            }

            AutorEvent.ResetSelectMode -> {
                resetSelectMode()
            }

            AutorEvent.StartSelectMode -> _uiState.update { it.copy(selectMode = true) }
        }
    }

    private fun generateRandomAuthor(): String {
        val faker = Faker()
        return faker.book().author()
    }

    private fun generateRandomDate(): String {
        val faker = Faker()
        val randomDate = faker.date().birthday()
        val dateFormat = SimpleDateFormat(Constants.DD_MM_YY, Locale.getDefault())
        return dateFormat.format(randomDate)
    }

    private fun addAutor() {
        viewModelScope.launch {
            try {
                insertAutorUseCase.invoke(Autor(0, generateRandomAuthor(), generateRandomDate(), false))
                getAutores()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun resetSelectMode() {
        _uiState.update { it.copy(selectMode = false, autoresSelected = selectedAutores) }
        selectedAutores.clear()
    }

    private fun getAutores() {
        viewModelScope.launch {
            try {
                val result = getAutoresUseCase.invoke()
                _uiState.update { it.copy(autores = result) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }

    }


    private fun deleteAutors() {
        viewModelScope.launch {
            val totalselected = selectedAutores.size
            for (autor in selectedAutores) {
                deleteAutorUseCase.invoke(autor)
            }
            _uiState.update { it.copy(error = TOTALDELETED + "$totalselected") }
            selectedAutores.clear()
            _uiState.update { it.copy(autoresSelected = selectedAutores.toList()) }
            getAutores()

        }

    }

    private fun deleteAutor(autor: Autor) {
        viewModelScope.launch {
            deleteAutorUseCase.invoke(autor)
            getAutores()
        }
    }


    private fun seleccionaAutor(autor: Autor) {
        if (isSelected(autor)) {
            selectedAutores.remove(autor)
            if (selectedAutores.isEmpty()) {
                _uiState.update { it.copy(selectMode = false) }
            }
        } else {
            selectedAutores.add(autor)
        }
        _uiState.update { it.copy(autoresSelected = selectedAutores) }
    }

    private fun isSelected(autor: Autor): Boolean {
        return selectedAutores.contains(autor)
    }


}