package com.sopt.domain.entity.request

data class LoginRequestModel(
    val authenticationId: String,
    val password: String,
)