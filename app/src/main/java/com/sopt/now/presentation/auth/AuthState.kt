package com.sopt.now.presentation.auth

sealed class AuthState {
    data object Success : AuthState()
    data object InputError : AuthState()
    data object Failure : AuthState()
}