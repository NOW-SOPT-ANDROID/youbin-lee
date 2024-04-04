package com.sopt.now.compose.presentation.auth.signup

sealed class SignUpState {
    data object Success: SignUpState()
    data object Failure: SignUpState()
}