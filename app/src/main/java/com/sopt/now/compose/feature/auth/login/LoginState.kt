package com.sopt.now.compose.feature.auth.login

data class LoginState(
    val id: String = "",
    val password: String = "",
    val nickname: String = "",
    val phone: String = "",
)