package com.sopt.now.data.datasource

import com.sopt.now.data.dto.request.SignUpRequestDto
import com.sopt.now.data.dto.response.SignUpResponseDto

interface AuthDataSource {
    suspend fun postSignUp(signUpRequestDto: SignUpRequestDto): SignUpResponseDto
}