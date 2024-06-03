package com.sopt.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.domain.entity.request.SignUpRequestModel
import com.sopt.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    private val _state: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState>
        get() = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<SignUpSideEffect> = MutableSharedFlow()
    val sideEffect: SharedFlow<SignUpSideEffect> get() = _sideEffect

    fun fetchId(id: String) {
        _state.value = _state.value.copy(id = id)
    }

    fun fetchPassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun fetchNickname(nickname: String) {
        _state.value = _state.value.copy(nickname = nickname)
    }

    fun fetchPhone(phone: String) {
        _state.value = _state.value.copy(phone = phone)
    }

    fun checkSignUpAvailable() {
        viewModelScope.launch {
            runCatching {
                repository.postSignUp(
                    state.value.run {
                        SignUpRequestModel(
                            id,
                            password,
                            nickname,
                            phone
                        )
                    }
                ).onSuccess {
                    _sideEffect.emit(SignUpSideEffect.Success(it.memberId))
                }.onFailure {
                    _sideEffect.emit(SignUpSideEffect.Failure(it.message.orEmpty()))
                }
            }
        }
    }

}