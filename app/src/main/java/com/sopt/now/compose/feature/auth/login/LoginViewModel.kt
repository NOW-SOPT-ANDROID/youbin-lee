package com.sopt.now.compose.feature.auth.login

import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState>
        get() = _state.asStateFlow()

    private val _defaultState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())

    fun setId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun setPassword(pw: String) {
        _state.value = _state.value.copy(pw = pw)
    }

    fun checkLogin() {
        _state.value = when {
            _state.value.id != _defaultState.value.id -> _state.value.copy(message = "ID가 일치하지 않습니다")
            _state.value.pw != _defaultState.value.pw -> _state.value.copy(message = "비밀번호가 일치하지 않습니다")
            else -> {
                _state.value = _state.value.copy(message = "로그인에 성공했습니다")
                _state.value
            }
        }
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
}