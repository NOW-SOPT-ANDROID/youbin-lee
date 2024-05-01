package com.sopt.now.data.service

import com.sopt.now.data.dto.request.LoginRequestDto
import com.sopt.now.data.dto.request.SignUpRequestDto
import com.sopt.now.data.dto.response.LoginResponseDto
import com.sopt.now.data.dto.response.SignUpResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun postSignUpToServer(
        @Body request: SignUpRequestDto,
    ): Response<SignUpResponseDto>

    @POST("member/login")
    suspend fun postLoginToServer(
        @Body request: LoginRequestDto,
    ): Response<LoginResponseDto>

}