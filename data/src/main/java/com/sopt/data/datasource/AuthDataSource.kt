package com.sopt.data.datasource

import com.sopt.data.dto.request.LoginRequestDto
import com.sopt.data.dto.request.SignUpRequestDto
import com.sopt.data.dto.response.LoginResponseDto
import com.sopt.data.dto.response.SignUpResponseDto
import retrofit2.Response


interface AuthDataSource {
    suspend fun postLogin(request: LoginRequestDto): Response<LoginResponseDto>
    suspend fun postSignUp(request: SignUpRequestDto): Response<SignUpResponseDto>
}