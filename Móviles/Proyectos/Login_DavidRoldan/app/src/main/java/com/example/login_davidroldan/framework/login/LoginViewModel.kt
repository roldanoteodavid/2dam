package com.example.login_davidroldan.framework.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_davidroldan.domain.modelo.Credentials
import com.example.login_davidroldan.domain.usecases.ForgotPasswordUseCase
import com.example.login_davidroldan.domain.usecases.LoginUseCase
import com.example.login_davidroldan.utils.Constants
import com.example.login_davidroldan.utils.NetworkResultt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    //private val _uiState = MutableLiveData(LoginState())
    //val uiState: LiveData<LoginState> get() = _uiState

    private val _uiState: MutableStateFlow<LoginState> by lazy {
        MutableStateFlow(LoginState())
    }
    val uiState: StateFlow<LoginState> = _uiState


    init {
        _uiState.value = LoginState(
            error = null,
            login = false,
            forgotPassword = false,
        )
    }

    fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> {
                login(event.user, event.password)
            }

            is LoginEvent.ForgotPassword -> {
                forgotPassword(event.user)
            }
            LoginEvent.ErrorVisto -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun login(user: String, pass: String) {
        if (user.isEmpty() || pass.isEmpty() || user.isBlank() || pass.isBlank()) {
            _uiState.update { it.copy(error = Constants.USER_OR_PASS_EMPTY) }
        }else{
            viewModelScope.launch {
                when (val result = loginUseCase.invoke(Credentials(user, "", pass, ""))) {
                    is NetworkResultt.Success -> {
                        _uiState.update { it.copy(error = null) }
                        _uiState.update { it.copy(login = true) }
                    }
                    is NetworkResultt.Error -> {
                        _uiState.update { it.copy(error = result.message) }
                    }
                }
            }
        }

    }

    private fun forgotPassword(user: String) {
        if (user.isEmpty() || user.isBlank()) {
            _uiState.update { it.copy(error = Constants.USER_EMPTY) }
        }else{
            viewModelScope.launch {
                when (val result = forgotPasswordUseCase.invoke(user)) {
                    is NetworkResultt.Success -> {
                        _uiState.update { it.copy(error = null) }
                        _uiState.update { it.copy(forgotPassword = true) }
                    }
                    is NetworkResultt.Error -> {
                        _uiState.update { it.copy(error = result.message) }
                    }
                }
            }
        }
    }


}