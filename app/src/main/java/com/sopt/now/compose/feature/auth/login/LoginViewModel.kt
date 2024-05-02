package com.sopt.now.compose.feature.auth.login

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.feature.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState>
        get() = _state.asStateFlow()

    private val _defaultState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())

    private val _sideEffect: MutableSharedFlow<LoginSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<LoginSideEffect> get() = _sideEffect.asSharedFlow()

    fun setId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun setPassword(pw: String) {
        _state.value = _state.value.copy(pw = pw)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun checkLogin() {
        when {
            _state.value.id != _defaultState.value.id -> _state.value.copy(message = "ID가 일치하지 않습니다")
            _state.value.pw != _defaultState.value.pw -> _state.value.copy(message = "비밀번호가 일치하지 않습니다")
            else -> _sideEffect.emit(LoginSideEffect.MainNavigation)
        }
        _sideEffect.resetReplayCache()
    }

    fun setUserInfo(user: User) {
        _state.value = _state.value.copy(
            id = user.id,
            pw = user.pw,
            nickname = user.nickname,
            mbti = user.mbti
        )

        _defaultState.value = _defaultState.value.copy(
            id = user.id,
            pw = user.pw,
            nickname = user.nickname,
            mbti = user.mbti
        )
    }

    fun clearMessage() {
        _state.value = _state.value.copy(message = null)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun signUpClick() {
        _sideEffect.emit(LoginSideEffect.SignUpNavigation)
        _sideEffect.resetReplayCache()
    }
}