package com.example.login_davidroldan.framework.changepassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_davidroldan.domain.modelo.Credentials
import com.example.login_davidroldan.domain.usecases.ChangePasswordUseCase
import com.example.login_davidroldan.utils.Constants
import com.example.login_davidroldan.utils.NetworkResultt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChangepassViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase,
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _uiState: MutableStateFlow<ChangepassState> by lazy {
        MutableStateFlow(ChangepassState())
    }
    val uiState: StateFlow<ChangepassState> = _uiState


    init {
        _uiState.value = ChangepassState(
            error = null,
            changepass = false,
        )
    }

    fun handleEvent(event: ChangepassEvent) {
        when (event) {
            is ChangepassEvent.Changepass -> {
                changePassword(event.username, event.password, event.temporal)
            }
            ChangepassEvent.ErrorVisto -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun changePassword(user: String, pass: String, temporal: String) {
        viewModelScope.launch {
            val result = changePasswordUseCase.invoke(Credentials(user, "", pass, temporal))
            when (result) {
                is NetworkResultt.Success -> {
                    _uiState.update { it.copy(error = Constants.PASSWORD_CHANGED) }
                    _uiState.update { it.copy(changepass = true) }
                }
                is NetworkResultt.Error -> {
                    _uiState.update { it.copy(error = result.message) }
                }
            }
        }
    }


}