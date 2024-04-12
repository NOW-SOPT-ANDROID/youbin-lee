package com.sopt.now.compose.presentation.auth.signup

data class SignUpState(
    val id: String = "",
    val password: String = "",
    val nickname: String = "",
    val mbti: String = "",
    val message: String? = null
)
