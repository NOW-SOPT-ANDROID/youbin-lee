package com.sopt.now.domain.entity.request

data class SignUpRequestModel(
    val id: String,
    val pw: String,
    val nickname: String,
    val phone: String
)
