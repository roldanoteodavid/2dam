package com.example.login_davidroldan.framework.twofactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_davidroldan.domain.modelo.Credentials
import com.example.login_davidroldan.domain.usecases.ForgotPasswordUseCase
import com.example.login_davidroldan.domain.usecases.LoginUseCase
import com.example.login_davidroldan.domain.usecases.TwoFaUseCase
import com.example.login_davidroldan.utils.Constants
import com.example.login_davidroldan.utils.NetworkResultt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TwoFactorViewModel @Inject constructor(
    private val twoFaUseCase: TwoFaUseCase,
) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    private val _uiState: MutableStateFlow<TwoFactorState> by lazy {
        MutableStateFlow(TwoFactorState())
    }
    val uiState: StateFlow<TwoFactorState> = _uiState


    init {
        _uiState.value = TwoFactorState(
            error = null,
            login = false,
        )
    }

    fun handleEvent(event: TwoFactorEvent) {
        when (event) {
            is TwoFactorEvent.TwoFactor -> {
                twoFactor(event.user, event.twofacode)
            }
            TwoFactorEvent.ErrorVisto -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun twoFactor(user: String, twofacode: String) {
        if (user.isEmpty() || twofacode.isEmpty() || user.isBlank() || twofacode.isBlank()) {
            _uiState.update { it.copy(error = Constants.USER_OR_TWOFA_EMPTY) }
        }else{
            viewModelScope.launch {
                when (val result = twoFaUseCase.invoke(Credentials(user, "", "", twofacode))) {
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


}