package com.sopt.now.data.dto.response

import com.sopt.now.domain.entity.response.SignUpResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponseDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
)
