package com.sopt.now.domain.entity.request

data class LoginRequestModel(
    val authenticationId: String,
    val password: String,
)