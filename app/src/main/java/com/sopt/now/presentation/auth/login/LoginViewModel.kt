package com.sopt.now.presentation.auth.login

import androidx.lifecycle.ViewModel
import com.sopt.now.presentation.User
import dagger.hilt.android.lifecycle.HiltViewModel

class LoginViewModel : ViewModel() {
//
//    private val _loginState = MutableSharedFlow<LoginState>()
//    val loginState: SharedFlow<LoginState> get() = _loginState

    private lateinit var user: User

    private var isIdCorrect = false
    private var isPwCorrect = false

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser() = user

//    fun checkLoginFormat() {
//        viewModelScope.launch {
//            val loginFormat = when {
//                !isIdCorrect -> LoginState.IdError
//                !isPwCorrect -> LoginState.PwError
//                else -> LoginState.Success
//            }
//            _loginState.emit(loginFormat)
//        }
//    }

    fun checkIdInput(id: String) {
        if (::user.isInitialized) isIdCorrect = id == user.id
    }

    fun checkPwInput(pw: String) {
        if (::user.isInitialized) isPwCorrect = user.pw == pw
    }

}