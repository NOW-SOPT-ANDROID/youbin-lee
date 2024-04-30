package com.sopt.now.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.entity.UserEntity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _signUpState = MutableSharedFlow<SignUpState>()
    val signUpState: SharedFlow<SignUpState> get() = _signUpState

    private lateinit var userEntity: UserEntity

    fun setUser(userEntity: UserEntity) {
        this.userEntity = userEntity
    }

    fun getUser() = userEntity

    fun checkSignUpFormat() {
        viewModelScope.launch {
            val signUpFormat = when {
                userEntity.id.length !in ID_MIN_LENGTH..ID_MAX_LENGTH -> SignUpState.IdError

                userEntity.pw.length !in PW_MIN_LENGTH..PW_MAX_LENGTH -> SignUpState.PwError

                userEntity.nickname.isEmpty() || userEntity.mbti.isEmpty() -> SignUpState.BlankError

                else -> SignUpState.Success
            }
            _signUpState.emit(signUpFormat)
        }
    }

    companion object {
        private const val ID_MIN_LENGTH = 6
        private const val ID_MAX_LENGTH = 10
        private const val PW_MIN_LENGTH = 8
        private const val PW_MAX_LENGTH = 12
    }

}