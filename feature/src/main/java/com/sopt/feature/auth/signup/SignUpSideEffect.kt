package com.sopt.feature.auth.signup

sealed class SignUpSideEffect {
    data class Success(val memberId: String? = null) : SignUpSideEffect()
    data class Failure(val message: String) : SignUpSideEffect()
}