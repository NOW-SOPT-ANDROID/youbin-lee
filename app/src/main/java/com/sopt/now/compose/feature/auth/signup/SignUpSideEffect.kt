package com.sopt.now.compose.feature.auth.signup

sealed class SignUpSideEffect {
    data class Success(val memberId: String ?= null) : SignUpSideEffect()
    data object InputError : SignUpSideEffect()
    data object Failure : SignUpSideEffect()
}