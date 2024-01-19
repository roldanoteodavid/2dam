package com.example.segundaapp_davidroldan.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.segundaapp_davidroldan.R
import com.example.segundaapp_davidroldan.domain.modelo.Simpson
import com.example.segundaapp_davidroldan.domain.usecases.simpsons.AddSimpsonUseCase
import com.example.segundaapp_davidroldan.domain.usecases.simpsons.DeleteSimpsonUseCase
import com.example.segundaapp_davidroldan.domain.usecases.simpsons.GetSimpsonUseCase
import com.example.segundaapp_davidroldan.domain.usecases.simpsons.GetSimpsons
import com.example.segundaapp_davidroldan.domain.usecases.simpsons.UpdateSimpsonUseCase
import com.example.segundaapp_davidroldan.utils.StringProvider

class MainViewModel(
    private val stringProvider: StringProvider,
    private val addSimpsonUseCase: AddSimpsonUseCase,
    private val getSimpsons: GetSimpsons,
    private val deleteSimpsonUseCase: DeleteSimpsonUseCase,
    private val updateSimpson: UpdateSimpsonUseCase,
    private val getSimpsonId: GetSimpsonUseCase,
) : ViewModel() {
    private var indice = 0
    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState

    init {
        _uiState.value = MainState(
            simpson = _uiState.value.let { getSimpsonId(0) },
            error = null,
            noanterior = false,
        )
    }

    fun addSimpson(simpson: Simpson) {
        if (simpson.nombre == "") {
            _uiState.value =
                _uiState.value?.copy(error = stringProvider.getString(R.string.rellenanombre))
        } else {
            if (!addSimpsonUseCase(simpson)) {
                _uiState.value =
                    _uiState.value?.copy(error = stringProvider.getString(R.string.erroranyadir))
            } else {
                if (getSimpsons.invoke().size == 1) {
                    _uiState.value = MainState(
                        simpson = _uiState.value.let { simpson },
                        error = stringProvider.getString(R.string.simpsonanyadido),
                        noanterior = false,
                        nosiguiente = false,
                        updatedelete = true,
                    )
                } else {
                    _uiState.value = MainState(
                        simpson = _uiState.value.let { simpson },
                        error = stringProvider.getString(R.string.simpsonanyadido),
                        noanterior = true,
                        nosiguiente = false,
                        updatedelete = true,
                    )
                }
                indice = getSimpsons.invoke().size - 1

            }
        }

    }

    fun deleteSimpson() {
        if (!deleteSimpsonUseCase(indice)) {
            _uiState.value = MainState(
                error = stringProvider.getString(R.string.anyadasimpson),
                noanterior = false,
                nosiguiente = false,
                updatedelete = false,
            )
        } else {
            if (indice != 0) {
                indice--
            }
            _uiState.value = MainState(
                simpson = _uiState.value.let { getSimpsonId(indice) },
                error = stringProvider.getString(R.string.simpsoneliminado),
            )
            if (indice == 0) {
                _uiState.value = _uiState.value?.copy(noanterior = false)
            }
            if (indice == getSimpsons.invoke().size - 1) {
                _uiState.value = _uiState.value?.copy(nosiguiente = false)
            }
            if (getSimpsons.invoke().isEmpty()) {
                _uiState.value = MainState(
                    error = stringProvider.getString(R.string.anyadasimpson),
                    noanterior = false,
                    nosiguiente = false,
                    updatedelete = false,
                )
            }
        }
    }

    fun updateSimpson(newsimpson: Simpson) {
        if (newsimpson.nombre == "") {
            _uiState.value =
                _uiState.value?.copy(error = stringProvider.getString(R.string.rellenanombre))
        } else {
            updateSimpson.invoke(_uiState.value?.simpson!!, newsimpson)
            _uiState.value =
                _uiState.value?.copy(error = stringProvider.getString(R.string.simpsonupdate))
            _uiState.value = _uiState.value?.copy(simpson = newsimpson)
        }

    }

    fun getSimpson(id: Int) {
        _uiState.value = MainState(
            simpson = _uiState.value.let { getSimpsonId(id) },
            error = null,
        )
    }

    fun getSiguienteSimpson() {
        if (indice < getSimpsons.invoke().size - 1) {
            _uiState.value = _uiState.value?.copy(noanterior = false)
            indice++
            getSimpson(indice)
            if (indice == getSimpsons.invoke().size - 1) {
                _uiState.value = _uiState.value?.copy(nosiguiente = false)
            }
        }
    }

    fun getAnteriorSimpson() {
        if (indice > 0) {
            _uiState.value = _uiState.value?.copy(nosiguiente = true)
            indice--
            getSimpson(indice)
            if (indice == 0) {
                _uiState.value = _uiState.value?.copy(noanterior = false)
            }
        }
    }


    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }

}


/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class MainViewModelFactory(
    private val stringProvider: StringProvider,
    private val addSimpson: AddSimpsonUseCase,
    private val getSimpsons: GetSimpsons,
    private val deleteSimpsons: DeleteSimpsonUseCase,
    private val updateSimpsons: UpdateSimpsonUseCase,
    private val getSimpson: GetSimpsonUseCase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                stringProvider,
                addSimpson,
                getSimpsons,
                deleteSimpsons,
                updateSimpsons,
                getSimpson,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}