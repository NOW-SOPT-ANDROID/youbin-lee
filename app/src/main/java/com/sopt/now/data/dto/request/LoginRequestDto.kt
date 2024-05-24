package com.sopt.now.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("password")
    val password: String,
)