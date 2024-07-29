package com.sopt.data.dto.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
)