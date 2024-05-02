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
    suspend fun checkLoginAvailable() {
        viewModelScope.launch {
            runCatching {
                authService.postLoginToServer(
                    state.value.run {
                        LoginRequestDto(
                            id,
                            pw
                        )
                    }

                )
            }
                .onSuccess {
                    when (it.body()?.code) {
                        in SERVER_MIN_CODE..SERVER_MAX_CODE ->
                            _sideEffect.emit(
                                LoginSideEffect.Success(
                                    it.headers()["Location"]?.split(
                                        "/"
                                    )?.last()
                                )
                            )


                        else -> _sideEffect.emit(LoginSideEffect.InputError)
                    }
                }
                .onFailure {
                    _sideEffect.emit(LoginSideEffect.Failure)
                }
        }

//        when {
//            _state.value.id != _defaultState.value.id -> _state.value.copy(message = "ID가 일치하지 않습니다")
//            _state.value.pw != _defaultState.value.pw -> _state.value.copy(message = "비밀번호가 일치하지 않습니다")
//            else -> _sideEffect.emit(LoginSideEffect.MainNavigation)
//        }
//        _sideEffect.resetReplayCache()
    }

//    fun setUserInfo(user: User) {
//        _state.value = _state.value.copy(
//            id = user.id,
//            pw = user.pw,
//            nickname = user.nickname,
//            mbti = user.phone
//        )
//
//        _defaultState.value = _defaultState.value.copy(
//            id = user.id,
//            pw = user.pw,
//            nickname = user.nickname,
//            mbti = user.phone
//        )
//    }

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