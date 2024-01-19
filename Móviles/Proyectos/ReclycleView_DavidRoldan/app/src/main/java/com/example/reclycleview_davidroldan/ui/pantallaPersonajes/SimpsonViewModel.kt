package com.example.reclycleview_davidroldan.ui.pantallaPersonajes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.reclycleview_davidroldan.R
import com.example.reclycleview_davidroldan.data.model.Simpsonjson
import com.example.reclycleview_davidroldan.domain.usecases.AddSimpsonUseCase
import com.example.reclycleview_davidroldan.domain.usecases.DeleteSimpsonUseCase
import com.example.reclycleview_davidroldan.domain.usecases.GetSimpsonUseCase
import com.example.reclycleview_davidroldan.utils.StringProvider
import com.example.segundaapp_davidroldan.domain.usecases.simpsons.UpdateSimpsonUseCase

class SimpsonViewModel(
    private val stringProvider: StringProvider,
    private val addSimpsonUseCase: AddSimpsonUseCase,
    private val deleteSimpsonUseCase: DeleteSimpsonUseCase,
    private val updateSimpson: UpdateSimpsonUseCase,
    private val getSimpsonUseCase: GetSimpsonUseCase,
) : ViewModel() {
    private val _uiState = MutableLiveData<SimpsonState>()
    val uiState: LiveData<SimpsonState> get() = _uiState

    init {
        _uiState.value = SimpsonState(
            error = null,
        )
    }

    fun addSimpson(simpson: Simpsonjson) {
        if (simpson.name == "") {
            _uiState.value =
                _uiState.value?.copy(error = stringProvider.getString(R.string.rellenanombre))
        } else {
            if (!addSimpsonUseCase(simpson)) {
                _uiState.value =
                    _uiState.value?.copy(error = stringProvider.getString(R.string.erroranyadir))
            } else {
                _uiState.value =
                    _uiState.value?.copy(error = stringProvider.getString(R.string.simpsonanyadido))
                _uiState.value = _uiState.value?.copy(simpson = simpson)

            }
        }

    }

    fun deleteSimpson() {
        val simpson = _uiState.value?.simpson
        if (simpson != null && deleteSimpsonUseCase(simpson)) {
            _uiState.value =
                _uiState.value?.copy(error = stringProvider.getString(R.string.simpsoneliminado))
        }
    }

    fun updateSimpson(newsimpson: Simpsonjson) {
        if (newsimpson.name == "") {
            _uiState.value =
                _uiState.value?.copy(error = stringProvider.getString(R.string.rellenanombre))
        } else {
            val simpsontoupdate = _uiState.value?.simpson
            if (simpsontoupdate != null) {
                updateSimpson.invoke(simpsontoupdate, newsimpson)
                _uiState.value =
                    _uiState.value?.copy(error = stringProvider.getString(R.string.simpsonupdate))
                _uiState.value = _uiState.value?.copy(simpson = newsimpson)
            }
        }

    }

    fun getSimpson(id: Int) {
        _uiState.value = SimpsonState(
            simpson = _uiState.value.let { getSimpsonUseCase(id) },
            error = null,
        )
    }


    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }
}

class SimpsonViewModelFactory(
    private val stringProvider: StringProvider,
    private val addSimpson: AddSimpsonUseCase,
    private val deleteSimpsons: DeleteSimpsonUseCase,
    private val updateSimpsons: UpdateSimpsonUseCase,
    private val getSimpson: GetSimpsonUseCase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SimpsonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SimpsonViewModel(
                stringProvider,
                addSimpson,
                deleteSimpsons,
                updateSimpsons,
                getSimpson,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}