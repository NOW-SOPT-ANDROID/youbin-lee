package com.sopt.now.compose.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponseDto(
    val code: Int,
    val message: String,
    val data: User,
) {
    @Serializable
    data class User(
        val authenticationId: String,
        val nickname: String,
        val phone: String,
    )
}