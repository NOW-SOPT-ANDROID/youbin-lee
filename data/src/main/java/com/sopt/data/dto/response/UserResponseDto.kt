package com.sopt.data.dto.response

import com.sopt.domain.entity.response.UserResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: User,
) {
    @Serializable
    data class User(
        @SerialName("authenticationId")
        val authenticationId: String,
        @SerialName("nickname")
        val nickname: String,
        @SerialName("phone")
        val phone: String,
    )

    fun toUserEntity(): UserResponseModel =
        UserResponseModel(
            nickname = data.nickname,
            phone = data.phone
        )
}