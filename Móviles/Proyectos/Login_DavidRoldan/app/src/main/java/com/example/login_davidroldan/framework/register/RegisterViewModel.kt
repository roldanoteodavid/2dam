package com.example.login_davidroldan.framework.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_davidroldan.domain.modelo.Credentials
import com.example.login_davidroldan.domain.usecases.RegisterUseCase
import com.example.login_davidroldan.utils.Constants
import com.example.login_davidroldan.utils.NetworkResultt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    private val _uiState: MutableStateFlow<RegisterState> by lazy {
        MutableStateFlow(RegisterState())
    }
    val uiState: StateFlow<RegisterState> get() = _uiState


    init {
        _uiState.value = RegisterState(
            register = false,
            error = null,
        )
    }

    fun handleEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.Register -> {
                register(event.user, event.email, event.password)
            }

            RegisterEvent.ErrorVisto -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun register(user: String, email: String, password: String) {
        if (user.isEmpty() || email.isEmpty() || password.isEmpty()) {
            _uiState.update { it.copy(error = Constants.RELLENE_TODOS_LOS_CAMPOS) }
        }else{
            viewModelScope.launch {
                when (val result = registerUseCase.invoke(Credentials(user, email, password, ""))) {
                    is NetworkResultt.Success -> {
                        _uiState.update { it.copy(error = Constants.ACTIVE_SU_CUENTA) }
                        _uiState.update { it.copy(register = true) }
                    }

                    is NetworkResultt.Error -> {
                        _uiState.update { it.copy(error = result.message) }
                    }
                }
            }
        }
    }


}