package com.sopt.now.compose.presentation.auth.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel : ViewModel() {
    private val _state: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState>
        get() = _state.asStateFlow()

    fun setId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun setPassword(pw: String) {
        _state.value = _state.value.copy(pw = pw)
    }

    fun setNickname(nickname: String) {
        _state.value = _state.value.copy(nickname = nickname)
    }

    fun setMbti(mbti: String) {
        _state.value = _state.value.copy(mbti = mbti)
    }

    fun checkSignUp() {
        _state.value = when {
            _state.value.id.length !in ID_MIN_LENGTH..ID_MAX_LENGTH ->
                _state.value.copy(message = "아이디는 6~10글자 이내로 입력해주세요.")

            _state.value.pw.length !in PW_MIN_LENGTH..PW_MAX_LENGTH ->
                _state.value.copy(message = "비밀번호는 8~12글자 이내로 입력해주세요.")

            _state.value.nickname.isBlank() ->
                _state.value.copy(message = "닉네임을 입력해주세요.")

            _state.value.mbti.isBlank() ->
                _state.value.copy(message = "MBTI를 입력해주세요.")

            else -> {
                _state.value = _state.value.copy(message = "회원가입에 성공했습니다")
                _state.value
            }
        }
    }

    fun clearMessage() {
        _state.value = _state.value.copy(message = null)
    }

    companion object {
        private const val ID_MIN_LENGTH = 6
        private const val ID_MAX_LENGTH = 10
        private const val PW_MIN_LENGTH = 8
        private const val PW_MAX_LENGTH = 12
    }

}