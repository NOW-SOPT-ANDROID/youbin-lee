package com.sopt.data.dto.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
)