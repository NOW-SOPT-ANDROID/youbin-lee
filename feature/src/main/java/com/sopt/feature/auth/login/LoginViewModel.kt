package com.sopt.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.domain.entity.request.LoginRequestModel
import com.sopt.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
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

    fun checkLoginAvailable() {
        viewModelScope.launch {
            repository.postLogin(
                state.value.run {
                    LoginRequestModel(
                        id,
                        password
                    )
                }
            ).onSuccess {
                _sideEffect.emit(LoginSideEffect.Success(it.memberId))
            }.onFailure {
                _sideEffect.emit(LoginSideEffect.Failure(it.message.orEmpty()))
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun signUpClick() {
        _sideEffect.emit(LoginSideEffect.SignUpNavigation)
        _sideEffect.resetReplayCache()
    }

}