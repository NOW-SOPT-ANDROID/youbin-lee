package com.sopt.now.compose.presentation.auth.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _state = MutableSharedFlow<SignUpState>()
    val state = _state.asSharedFlow()

    var userId = mutableStateOf("")
    var password = mutableStateOf("")
    var nickname = mutableStateOf("")
    var mbti = mutableStateOf("")

    fun onSignUpClicked() {
        viewModelScope.launch {
            if (isSignUpValid()) {
                _state.emit(SignUpState.Success)
            } else {
                _state.emit(SignUpState.Failure)
            }
        }
    }

    private fun isSignUpValid(): Boolean {
        return userId.value.isNotBlank() && password.value.isNotBlank() && nickname.value.isNotBlank() && mbti.value.isNotBlank()
    }
}