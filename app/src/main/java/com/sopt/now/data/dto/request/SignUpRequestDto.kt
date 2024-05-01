package com.sopt.now.data.dto.request

import com.sopt.now.domain.entity.request.SignUpRequestModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequestDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("password")
    val password: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("phone")
    val phone: String,
)

fun SignUpRequestModel.toSignUpRequestDto(): SignUpRequestDto =
    SignUpRequestDto(id, pw, nickname, phone)