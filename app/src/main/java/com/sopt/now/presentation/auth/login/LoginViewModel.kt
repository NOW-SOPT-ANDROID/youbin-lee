package com.sopt.now.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.entity.UserEntity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginState = MutableSharedFlow<LoginState>()
    val loginState: SharedFlow<LoginState> get() = _loginState

    private lateinit var userEntity: UserEntity

    private var isIdCorrect = false
    private var isPwCorrect = false

    fun setUser(userEntity: UserEntity) {
        this.userEntity = userEntity
    }

    fun getUser() = userEntity

    fun checkLoginFormat() {
        viewModelScope.launch {
            val loginFormat = when {
                !isIdCorrect -> LoginState.IdError
                !isPwCorrect -> LoginState.PwError
                else -> LoginState.Success
            }
            _loginState.emit(loginFormat)
        }
    }

    fun checkIdInput(id: String) {
        if (::userEntity.isInitialized) isIdCorrect = id == userEntity.id
    }

    fun checkPwInput(pw: String) {
        if (::userEntity.isInitialized) isPwCorrect = userEntity.pw == pw
    }

}