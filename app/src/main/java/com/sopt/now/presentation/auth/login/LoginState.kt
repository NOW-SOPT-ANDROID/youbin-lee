package com.sopt.now.presentation.auth.login

sealed class LoginState {
    data object IdError : LoginState()
    data object PwError : LoginState()
    data object Success : LoginState()
}
