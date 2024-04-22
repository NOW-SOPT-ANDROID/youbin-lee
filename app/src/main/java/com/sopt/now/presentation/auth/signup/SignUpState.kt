package com.sopt.now.presentation.auth.signup

sealed class SignUpState {
    data object IdError : SignUpState()
    data object PwError : SignUpState()
    data object BlankError : SignUpState()
    data object Success :SignUpState()
}

