package com.example.reclycleview_davidroldan.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.reclycleview_davidroldan.domain.usecases.GetSimpsonsUseCase
import com.example.reclycleview_davidroldan.utils.StringProvider

class MainViewModel(
    private val stringProvider: StringProvider,
    private val getSimpsonsUseCase: GetSimpsonsUseCase,
) : ViewModel() {
    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState

    init {
        _uiState.value = MainState(
            error = null,
        )
    }

    fun getSimpsons() {
        _uiState.value = _uiState.value?.copy(lista = getSimpsonsUseCase())
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
    private val getSimpsonsUseCase: GetSimpsonsUseCase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                stringProvider,
                getSimpsonsUseCase,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}