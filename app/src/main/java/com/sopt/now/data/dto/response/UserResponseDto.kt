package com.sopt.now.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    val code: Int,
    val message: String,
    val data: UserData,
) {
    @Serializable
    data class UserData(
        val authenticationId: String,
        val nickname: String,
        val phone: String,
    )
}