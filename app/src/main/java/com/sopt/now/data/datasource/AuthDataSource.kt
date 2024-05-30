package com.sopt.now.data.datasource

import com.sopt.now.data.dto.request.SignUpRequestDto
import com.sopt.now.data.dto.response.SignUpResponseDto
import retrofit2.Response

interface AuthDataSource {
    suspend fun postSignUp (request : SignUpRequestDto) : Response<SignUpResponseDto>
}