package com.sopt.now.compose.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.di.ServicePool.authService
import com.sopt.now.compose.data.dto.request.LoginRequestDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<LoginSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<LoginSideEffect> get() = _sideEffect.asSharedFlow()

    fun fetchId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun fetchPassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun checkLoginAvailable() {
        viewModelScope.launch {
            runCatching {
                authService.postLoginToServer(
                    state.value.run {
                        LoginRequestDto(
                            id,
                            password
                        )
                    }
                )
            }.onSuccess {
                when (it.body()?.code) {
                    in SERVER_MIN_CODE..SERVER_MAX_CODE -> _sideEffect.emit(
                        LoginSideEffect.Success(
                            it.headers()["Location"]
                        )
                    )

                    else -> _sideEffect.emit(LoginSideEffect.ErrorToast(it.message()))
                }
            }.onFailure {
                _sideEffect.emit(LoginSideEffect.Failure)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun signUpClick() {
        _sideEffect.emit(LoginSideEffect.SignUpNavigation)
        _sideEffect.resetReplayCache()
    }

    companion object {
        private const val SERVER_MIN_CODE = 200
        private const val SERVER_MAX_CODE = 209
    }
}