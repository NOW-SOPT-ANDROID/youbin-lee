package com.sopt.now.compose.feature.auth.login

data class LoginState(
    val id: String = "",
    val pw: String = "",
    val nickname: String = "",
    val mbti: String = "",
    val message: String? = null
)