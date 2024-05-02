package com.sopt.now.compose.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.data.di.ServicePool.authService
import com.sopt.now.compose.data.dto.request.SignUpRequestDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    fun setPassword(pw: String) {
        _state.value = _state.value.copy(pw = pw)
    }

    fun setNickname(nickname: String) {
        _state.value = _state.value.copy(nickname = nickname)
    }

    fun setPhone(phone: String) {
        _state.value = _state.value.copy(phone = phone)
    }

//    fun setMemberId(memberId: String) {
//        _state.value = _state.value.copy(memberId = memberId)
//    }

    suspend fun checkSignUp() {
        viewModelScope.launch {
            runCatching {
                authService.postSignUpToServer(
                    state.value.run {
                        SignUpRequestDto(
                            id,
                            pw,
                            nickname,
                            phone
                        )
                    }
                )
            }
                .onSuccess {
                    when (it.body()?.code) {
                        in SERVER_MIN_CODE..SERVER_MAX_CODE -> {
                            _sideEffect.emit(
                                SignUpSideEffect.Success(
                                    it.headers()["Location"]?.split(
                                        "/"
                                    )?.last()
                                )
                            )
                        }

                        else -> _sideEffect.emit(SignUpSideEffect.InputError)
                    }
                }
                .onFailure {
                    _sideEffect.emit(SignUpSideEffect.Failure)
                }
        }
    }

    companion object {
        private const val SERVER_MIN_CODE = 200
        private const val SERVER_MAX_CODE = 209
    }
//        when {
//            _state.value.id.length !in ID_MIN_LENGTH..ID_MAX_LENGTH ->
//                _state.value.copy(message = "아이디는 6~10글자 이내로 입력해주세요.")
//
//            else -> _sideEffect.emit(SignUpSideEffect.LoginNavigation)
//        }
//        _sideEffect.resetReplayCache()
//    }

//    companion object {
//        private const val ID_MIN_LENGTH = 6
//        private const val ID_MAX_LENGTH = 10
//        private const val PW_MIN_LENGTH = 8
//        private const val PW_MAX_LENGTH = 12
//    }

}