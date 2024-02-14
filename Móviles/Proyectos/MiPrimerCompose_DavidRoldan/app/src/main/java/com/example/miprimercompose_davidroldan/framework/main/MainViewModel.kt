package com.example.miprimercompose_davidroldan.framework.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.miprimercompose_davidroldan.common.Constantes
import com.example.miprimercompose_davidroldan.domain.modelo.Simpson
import com.example.miprimercompose_davidroldan.domain.usecases.AddSimpsonUseCase
import com.example.miprimercompose_davidroldan.domain.usecases.DeleteSimpsonUseCase
import com.example.miprimercompose_davidroldan.domain.usecases.GetAnteriorSimpsonUseCase
import com.example.miprimercompose_davidroldan.domain.usecases.GetSiguienteSimpsonUseCase
import com.example.miprimercompose_davidroldan.domain.usecases.UpdateSimpsonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val addSimpsonUseCase: AddSimpsonUseCase,
    private val getSiguienteSimpsonUseCase: GetSiguienteSimpsonUseCase,
    private val getAnteriorSimpsonUseCase: GetAnteriorSimpsonUseCase,
    private val updateSimpsonUseCase: UpdateSimpsonUseCase,
    private val deleteSimpsonUseCase: DeleteSimpsonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = MainState(
            error = null,
            noanterior = true,
            simpson = Simpson(),
            updatedelete = true,
        )
        getSimpsonInicio()
    }

    fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetSiguienteSimpson -> {
                getSiguienteSimpson()
            }

            is MainEvent.GetAnteriorSimpson -> {
                getAnteriorSimpson()
            }

            is MainEvent.AddSimpson -> {
                addSimpson()
            }

            is MainEvent.UpdateSimpson -> {
                updateSimpson()
            }

            is MainEvent.DeleteSimpson -> {
                deleteSimpson()
            }

            is MainEvent.OnNameChange -> {
                _uiState.update { it.copy(simpson = it.simpson.copy(nombre = event.name)) }
            }

            is MainEvent.OnEdadChange -> {
                _uiState.update { it.copy(simpson = it.simpson.copy(edad = event.edad.toInt())) }
            }

            is MainEvent.OnVivoChange -> {
                _uiState.update { it.copy(simpson = it.simpson.copy(vivo = event.vivo)) }
            }

            is MainEvent.OnGeneroChange -> {
                _uiState.update { it.copy(simpson = it.simpson.copy(genero = event.genero)) }
            }

            is MainEvent.OnProfesionChange -> {
                _uiState.update { it.copy(simpson = it.simpson.copy(profesion = event.profesion)) }
            }

            MainEvent.ErrorVisto -> _uiState.value = _uiState.value.copy(error = null)
        }
    }

    private fun deleteSimpson() {
        viewModelScope.launch {
            try {
                val simpson = _uiState.value.simpson
                deleteSimpsonUseCase.invoke(simpson)
                _uiState.update { it.copy(error = Constantes.SIMPSON_ELIMINADO) }
                getAnteriorSimpson()
                if (_uiState.value.simpson.id == simpson.id) {
                    getSiguienteSimpson()
                }
                if (getAnteriorSimpsonUseCase.invoke(simpson.id) != null)
                    _uiState.update { it.copy(noanterior = false) }
                else
                    _uiState.update { it.copy(noanterior = true) }
                if (getSiguienteSimpsonUseCase.invoke(simpson.id) != null)
                    _uiState.update { it.copy(nosiguiente = false) }
                else
                    _uiState.update { it.copy(nosiguiente = true) }
                if (getSiguienteSimpsonUseCase.invoke(simpson.id) == null && getAnteriorSimpsonUseCase.invoke(
                        simpson.id
                    ) == null
                ) {
                    _uiState.update { it.copy(updatedelete = false) }
                    _uiState.update { it.copy(simpson = Simpson()) }
                    _uiState.update { it.copy(error = Constantes.NO_HAY_SIMPSONS) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun updateSimpson() {
        val simpson = _uiState.value.simpson
        viewModelScope.launch {
            try {
                updateSimpsonUseCase.invoke(simpson)
                _uiState.update { it.copy(error = Constantes.SIMPSON_ACTUALIZADO) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun addSimpson() {
        val simpson = _uiState.value.simpson
        viewModelScope.launch {
            try {
                val result = addSimpsonUseCase.invoke(simpson)
                _uiState.update {
                    it.copy(
                        error = Constantes.SIMPSON_ANYADIDO,
                        simpson = result,
                        updatedelete = true,
                        nosiguiente = true
                    )
                }
                if (getAnteriorSimpsonUseCase.invoke(result.id) != null)
                    _uiState.update { it.copy(noanterior = false) }
                else
                    _uiState.update { it.copy(noanterior = true) }

            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun getAnteriorSimpson() {
        val simpson = _uiState.value.simpson
        viewModelScope.launch {
            try {
                val result = getAnteriorSimpsonUseCase.invoke(simpson.id)
                if (result == null) {
                    _uiState.update { it.copy(error = Constantes.NO_HAY_SIMPSONS) }
                } else {
                    _uiState.update { it.copy(simpson = result) }
                    if (getAnteriorSimpsonUseCase.invoke(result.id) == null)
                        _uiState.update { it.copy(noanterior = true) }
                    else
                        _uiState.update { it.copy(noanterior = false) }
                    if (getSiguienteSimpsonUseCase.invoke(result.id) == null)
                        _uiState.update { it.copy(nosiguiente = true) }
                    else
                        _uiState.update { it.copy(nosiguiente = false) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun getSiguienteSimpson() {
        val simpson = _uiState.value.simpson
        viewModelScope.launch {
            try {
                val result = getSiguienteSimpsonUseCase.invoke(simpson.id)
                if (result == null) {
                    _uiState.update { it.copy(error = Constantes.NO_HAY_SIMPSONS) }
                } else {
                    _uiState.update { it.copy(simpson = result) }
                    if (getSiguienteSimpsonUseCase.invoke(result.id) == null)
                        _uiState.update { it.copy(nosiguiente = true) }
                    else
                        _uiState.update { it.copy(nosiguiente = false) }
                    if (getAnteriorSimpsonUseCase.invoke(result.id) == null)
                        _uiState.update { it.copy(noanterior = true) }
                    else
                        _uiState.update { it.copy(noanterior = false) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    private fun getSimpsonInicio(){
        viewModelScope.launch {
            try {
                val result = getSiguienteSimpsonUseCase.invoke(0)
                if (result == null) {
                    _uiState.update { it.copy(error = Constantes.NO_HAY_SIMPSONS) }
                    _uiState.update { it.copy(updatedelete = false) }
                } else {
                    _uiState.update { it.copy(simpson = result) }
                    if (getSiguienteSimpsonUseCase.invoke(result.id) == null)
                        _uiState.update { it.copy(nosiguiente = true) }
                    else
                        _uiState.update { it.copy(nosiguiente = false) }
                    if (getAnteriorSimpsonUseCase.invoke(result.id) == null)
                        _uiState.update { it.copy(noanterior = true) }
                    else
                        _uiState.update { it.copy(noanterior = false) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
}