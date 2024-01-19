package com.example.miprimeraapp_davidroldn.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.miprimeraapp_davidroldn.utils.StringProvider

class MainViewModel(
    private val stringProvider: StringProvider,
) : ViewModel() {

    private val _uiState = MutableLiveData<MainState>()
    val uiState: LiveData<MainState> get() = _uiState
}