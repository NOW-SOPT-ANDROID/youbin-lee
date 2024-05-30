package com.sopt.now.presentation.auth

sealed class AuthState {
    data object Success : AuthState()
    data class Failure(val message: String) : AuthState()
}