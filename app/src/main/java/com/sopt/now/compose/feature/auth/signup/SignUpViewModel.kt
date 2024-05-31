package com.sopt.now.compose.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.di.ServicePool.authService
import com.sopt.now.compose.data.dto.request.SignUpRequestDto
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _state: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SignUpSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SignUpSideEffect> get() = _sideEffect

    fun setId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun setPassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun setNickname(nickname: String) {
        _state.value = _state.value.copy(nickname = nickname)
    }

    fun setPhone(phone: String) {
        _state.value = _state.value.copy(phone = phone)
    }

    fun checkSignUpAvailable() {
        viewModelScope.launch {
            runCatching {
                authService.postSignUpToServer(
                    state.value.run {
                        SignUpRequestDto(
                            id,
                            password,
                            nickname,
                            phone
                        )
                    }
                )
            }.onSuccess {
                when (it.body()?.code) {
                    in SERVER_MIN_CODE..SERVER_MAX_CODE -> _sideEffect.emit(
                        SignUpSideEffect.Success(
                            it.headers()["Location"]
                        )
                    )

                    else -> _sideEffect.emit(SignUpSideEffect.InputError)
                }
            }.onFailure {
                _sideEffect.emit(SignUpSideEffect.Failure)
            }
        }
    }

    companion object {
        private const val SERVER_MIN_CODE = 200
        private const val SERVER_MAX_CODE = 209
    }

}